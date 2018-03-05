package com.controller;


import com.domain.RailStatus;
import com.domain.StationCode;
import com.domain.TrainDetails;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.mashape.unirest.request.GetRequest;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@Component
public class StatusReceiverImpl implements StatusReceiver {

    private static final Logger LOGGER = LogManager.getLogger(StatusReceiverImpl.class);

    @Value("${transportApi.app.live.trains.url}")
    String transportApiUrl;

    @Value("${transportApi.app.id}")
    String transportApiId;

    @Value("${transportApi.app.key}")
    String transportApiKey;

    ObjectMapper mapper = new MyObjectMapper();

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

            //mapper.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
            RailStatus railStatus = mapper.readValue(body.toString(), RailStatus.class);


            System.out.println(railStatus);


        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    private class MyObjectMapper extends ObjectMapper {

        private com.fasterxml.jackson.databind.ObjectMapper jacksonObjectMapper
                = new com.fasterxml.jackson.databind.ObjectMapper();

        public <T> T readValue(String value, Class<T> valueType) {
            try {
                T rootNode = jacksonObjectMapper.readValue(value, valueType);

//                if (rootNode instanceof JsonNode) {
//                    System.out.println(((JsonNode)rootNode).toString());
//                }
//
//                if (rootNode instanceof JSONObject) {
//                    System.out.println(((JsonNode)rootNode).toString());
//                }
//
//                if (rootNode instanceof ArrayNode) {
//
//                    TrainDetails[] trainDetailses = mapper.readValue(rootNode.toString(), TrainDetails[].class);
//                    System.out.println(trainDetailses);
//                }




                return rootNode;
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

    }


}
