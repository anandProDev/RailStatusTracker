package com.service;


import com.google.common.base.Splitter;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;
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

import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.*;

@Component
public class StatusReceiverImpl implements StatusReceiver {

    private static final Logger LOGGER = LogManager.getLogger(StatusReceiverImpl.class);
    private final String transportApiId;
    private final String transportApiKey;
    private final String startTime;
    private final String endTime;
    private final ObjectMapper mapper;
    private final DelayTrackerServiceImpl delayTrackerServiceImpl;
    private final String transportApiUrl;

    SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm") ;

    int requestCount = 0;
    List<ConfigMapperHolder> configMapperHolder = new ArrayList<>();

    @Autowired
    public StatusReceiverImpl(@Value("${transportApi.app.live.trains.url}") String transportApiUrl,
                              @Value("${transportApi.app.id}") String transportApiId,
                              @Value("${transportApi.app.key}")String transportApiKey,
                              @Value("${app.source.destination.map}") String sourceDestinationList,
                              @Value("${application.process.startTime}") String startTime,
                              @Value("${application.process.endTime}") String endTime,
                              ObjectMapper mapper,
                              DelayTrackerServiceImpl delayTrackerServiceImpl) {
        this.transportApiUrl = transportApiUrl;
        this.transportApiId = transportApiId;
        this.transportApiKey = transportApiKey;
        this.startTime = startTime;
        this.endTime = endTime;
        Splitter.on(",").split(sourceDestinationList)
                .forEach(data -> {String[] values = data.split(":");
                    configMapperHolder.add(new ConfigMapperHolder(values[0], values[1]));
                        }
                );
        this.mapper = mapper;
        this.delayTrackerServiceImpl = delayTrackerServiceImpl;
    }

    @Override
    public void receiveFeeds() {
        getStatus();
    }

    private boolean withinTimeRange() {

        try{

            Date now = dateFormat.parse(dateFormat.format(new Date(Calendar.getInstance().getTimeInMillis())));

            if(now.after(dateFormat.parse(startTime)) &&
                now.before(dateFormat.parse(endTime)))
            return true;

        }catch (Exception e){
            LOGGER.info("Outside time range");
            return false;
        }
        LOGGER.info("Outside time range");
        return false;
    }

    @Scheduled(cron = "${app.call.transportApi.cron}")
    public void getStatus() {
        LOGGER.info("Get status call");

        if(!withinTimeRange())
            return;
        //https://transportapi.com/v3/uk/train/station/{from}/live.json?
        // app_id={app_id}&app_key={app_kep}&darwin=true&
        // destination={destination}&train_status=passenger
        configMapperHolder.forEach(config -> {

            Map<String, String> fields = new HashMap<>();

            fields.put("from", config.getKey());
            fields.put("app_id", transportApiId);
            fields.put("app_key", transportApiKey);
            fields.put("destination", config.getValue());

            try {
                GetRequest getRequest = Unirest.get(UriComponentsBuilder.fromUriString(transportApiUrl).buildAndExpand(fields).toString());

                System.out.println("Request count : " + ++requestCount);
                System.out.println("RequestTime:" + Calendar.getInstance().getTime());

                HttpResponse<JsonNode> jsonNodeHttpResponse = getRequest.asJson();

                RailStatus railStatus = mapper.readValue(jsonNodeHttpResponse.getBody().toString(), RailStatus.class);

                delayTrackerServiceImpl.processDelays(railStatus);

            } catch (UnknownHostException unknownHostException){
                LOGGER.error("Could not connect to host ", unknownHostException);
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    class ConfigMapperHolder{

        private final String key;
        private final String value;

        public String getKey() {
            return key;
        }

        public String getValue() {
            return value;
        }

        public ConfigMapperHolder(String key, String value){
            this.key = key;
            this.value = value;
        }
    }
}
