package com.service;


import com.domain.RailStatus;
import com.domain.StationCode;
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

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Component
public class StatusReceiverImpl implements StatusReceiver {

    private static final Logger LOGGER = LogManager.getLogger(StatusReceiverImpl.class);

    @Value("${transportApi.app.live.trains.url}")
    String transportApiUrl;

    @Value("${transportApi.app.id}")
    String transportApiId;

    @Value("${transportApi.app.key}")
    String transportApiKey;

    @Autowired
    ObjectMapper mapper;

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
        fields.put("from", StationCode.LEEDS.getCode());
        fields.put("app_id", transportApiId);
        fields.put("app_key", transportApiKey);
        fields.put("destination", StationCode.Sheffield.getCode());

        try {

            UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(transportApiUrl)
                    .queryParam("from", StationCode.LEEDS.getCode())
                    .queryParam("app_id", transportApiId)
                    .queryParam("app_key", transportApiKey)
                    .queryParam("destination", StationCode.Sheffield.getCode());


            GetRequest getRequest = Unirest.get(builder.buildAndExpand(fields).toString());
            HttpResponse<JsonNode> jsonNodeHttpResponse = getRequest.asJson();

            JsonNode body = jsonNodeHttpResponse.getBody();

            RailStatus railStatus = mapper.readValue(body.toString(), RailStatus.class);




        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
