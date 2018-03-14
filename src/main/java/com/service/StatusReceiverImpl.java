package com.service;


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
    public static final String DESTINATION = "DON";

    @Value("${transportApi.app.live.trains.url}")
    String transportApiUrl;

    @Value("${transportApi.app.id}")
    String transportApiId;

    @Value("${transportApi.app.key}")
    String transportApiKey;

    @Autowired
    ObjectMapper mapper;

    @Autowired
    DelayTrackerServiceImpl delayTrackerServiceImpl;

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

      //  fields.put("from", StationCode.LEEDS.getCode());
        fields.put("from", SOURCE);
        fields.put("app_id", transportApiId);
        fields.put("app_key", transportApiKey);
        fields.put("destination", DESTINATION);
       // fields.put("destination", StationCode.Sheffield.getCode());



        try {

            UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(transportApiUrl)
                   // .queryParam("from", StationCode.LEEDS.getCode())
                    .queryParam("from", SOURCE)
                    .queryParam("app_id", transportApiId)
                    .queryParam("app_key", transportApiKey)
                 //   .queryParam("destination", StationCode.Sheffield.getCode());
                    .queryParam("destination", DESTINATION);


            GetRequest getRequest = Unirest.get(builder.buildAndExpand(fields).toString());
            HttpResponse<JsonNode> jsonNodeHttpResponse = getRequest.asJson();

            JsonNode body = jsonNodeHttpResponse.getBody();

            RailStatus railStatus = mapper.readValue(body.toString(), RailStatus.class);

            delayTrackerServiceImpl.processDelays(railStatus);


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
