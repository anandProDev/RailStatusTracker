package com.service;


import com.google.common.base.Splitter;
import com.model.RailStatus;
import com.model.StationCode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.request.GetRequest;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.HashMap;
import java.util.Map;

@Component
public class StatusReceiverImpl implements StatusReceiver {

    private static final Logger LOGGER = LogManager.getLogger(StatusReceiverImpl.class);
    public static final String SOURCE = "LDS";
    public static final String DESTINATION = "SHF";

    private final String transportApiId;
    private final String transportApiKey;
    private final ObjectMapper mapper;
    private final DelayTrackerServiceImpl delayTrackerServiceImpl;
    private final String transportApiUrl;
    Map<String, String> sourceDestinationMap = new HashMap();

    @Autowired
    public StatusReceiverImpl(@Value("${transportApi.app.live.trains.url}") String transportApiUrl,
                              @Value("${transportApi.app.id}") String transportApiId,
                              @Value("${transportApi.app.key}")String transportApiKey,
                              @Value("${app.source.destination.map}") String sourceDestinationList,
                              ObjectMapper mapper,
                              DelayTrackerServiceImpl delayTrackerServiceImpl) {
        this.transportApiUrl = transportApiUrl;
        this.transportApiId = transportApiId;
        this.transportApiKey = transportApiKey;
        Splitter.on(",").split(sourceDestinationList)
                .forEach(data -> {String[] values = data.split(":");
                    sourceDestinationMap.put(values[0], values[1]);
                        }
                );
        this.mapper = mapper;
        this.delayTrackerServiceImpl = delayTrackerServiceImpl;
    }

    @Override
    public void receiveFeeds() {

        getStatus();
    }

    @Scheduled(cron = "${app.call.transportApi.cron}")
    public void getStatus() {
        LOGGER.info("Get status call");

        //https://transportapi.com/v3/uk/train/station/{from}/live.json?
        // app_id={app_id}&app_key={app_kep}&darwin=true&
        // destination={destination}&train_status=passenger

        Map<String, String> fields = new HashMap<>();

        fields.put("from", SOURCE);
        fields.put("app_id", transportApiId);
        fields.put("app_key", transportApiKey);
        fields.put("destination", DESTINATION);

        try {
            GetRequest getRequest = Unirest.get(UriComponentsBuilder.fromUriString(transportApiUrl).buildAndExpand(fields).toString());
            HttpResponse<JsonNode> jsonNodeHttpResponse = getRequest.asJson();

            RailStatus railStatus = mapper.readValue(jsonNodeHttpResponse.getBody().toString(), RailStatus.class);

            delayTrackerServiceImpl.processDelays(railStatus);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
