package com.db.mongo;

import com.db.PersistanceService;
import com.domain.DelayedService;
import com.domain.DelayedServiceHolder;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.bson.types.ObjectId;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Repository
public class MongoPersistanceServiceImpl implements PersistanceService {

    private final ObjectMapper objectMapper;
    private final MongoClient mongoClient;
    private final MongoDatabase database;
    private final MongoCollection<Document> collection;

    @Autowired
    public MongoPersistanceServiceImpl(@Value("${spring.data.mongodb.host}") String host,
                                       @Value("${spring.data.mongodb.port}") int port,
                                       @Value("${spring.data.mongodb.database}") String databaseName,
                                       @Value("${spring.data.mongodb.database.collectionName}") String collectionName,
                                       ObjectMapper objectMapper){
        mongoClient = new MongoClient( host , port);
        database = mongoClient.getDatabase(databaseName);
        collection = database.getCollection(collectionName);
        this.objectMapper = objectMapper;
    }

    private <T> T unmarshallDocument(Class<T> type, Document document) {

        try {
            String json = document.toString();
            return objectMapper.readValue(json, type);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public <T> T get(String id, Class<T> c) {
        T result = null;
        try {
            BasicDBObject query = new BasicDBObject();
            query.put("_id", new ObjectId(id));

            FindIterable<Document> documents = collection.find(query);

            Document document = documents.first();

            if (document != null) {
                result = unmarshallDocument(c, document);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }


    @Override
    public void updateRailDetails(DelayedServiceHolder delayedServiceHolder) {

        DelayedServiceHolder fromCB = get(delayedServiceHolder.getDate(), DelayedServiceHolder.class);

        if(fromCB == null){
            insert(delayedServiceHolder.getDate(),delayedServiceHolder);
        } else {
            update(fromCB, delayedServiceHolder);
            update(fromCB.getDate(), fromCB);
        }
    }

    @NotNull
    @Override
    public DelayedServiceHolder getDetails(@NotNull String date) {

        return null;
    }

    private void insert(final String id, Object object) {
        Document document = new Document();
        document.put(id, object);
        collection.insertOne(document);
    }

    private void update(final String id, Object object) {

        BasicDBObject query = new BasicDBObject();
        query.put("_id", new ObjectId(id));

        Document document = new Document();
        document.put(id, object);

        collection.findOneAndReplace(query, document);
    }

    private void update(DelayedServiceHolder fromCB, DelayedServiceHolder delayedServiceHolder) {

        Map<String, List<DelayedService>> delayedServices = delayedServiceHolder.getDelayedServices();

        delayedServices.forEach((k,v) -> add(fromCB, v.get(0)));
    }


    private void add(DelayedServiceHolder fromCB, DelayedService delayedService){

        List<DelayedService> delayedServices = fromCB.getDelayedServices().get(delayedService.getTrainNumber());

        if(delayedServices!=null && delayedServices.isEmpty()){
            fromCB.getDelayedServices().put(delayedService.getTrainNumber(), delayedServices);
            return;
        } else {
            delayedServices = new ArrayList<>();
            delayedServices.add(delayedService);
            fromCB.getDelayedServices().put(delayedService.getTrainNumber(), delayedServices);
        }
    }
}