//package com.db;
//
//import com.domain.DelayedServiceHolder;
//import com.model.RailDetailsHolder;
//import com.mongodb.*;
//import com.mongodb.client.MongoDatabase;
//import org.bson.Document;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.mongodb.core.MongoTemplate;
//import org.springframework.data.mongodb.core.query.Criteria;
//import org.springframework.data.mongodb.core.query.Query;
//import org.springframework.data.mongodb.core.query.Update;
//import org.springframework.stereotype.Component;
//
//import java.time.LocalDate;
//import java.time.format.DateTimeFormatter;
//import java.util.HashMap;
//import java.util.Map;
//
//@Component
//public class PersistanceServiceRepositoryImpl implements PersistanceService {
//
//    @Autowired
//    MongoTemplate mongoTemplate;
//
//    @Override
//    public int updateRailDetails(DelayedServiceHolder delayedServiceHolder){
//
//
//        MongoClient mongoClient = new MongoClient( "localhost" , 27017 );
//
//        MongoDatabase test1 = mongoClient.getDatabase("test");
//
//
//
//        Map<String, Object> map = new HashMap<>();
//        map.put(getTodaysDate(), delayedServiceHolder);
//
//        test1.getCollection("mycollection").insertOne(new Document(map));
//
//        String todaysDate = getTodaysDate();
//        Query query = new Query(Criteria.where("date").is(todaysDate));
//        Update update = new Update();
//        update.set(delayedServiceHolder.getDate(), delayedServiceHolder);
//
//        WriteResult result = mongoTemplate.updateFirst(query, update, RailDetailsHolder.class);
//
//        if(result!=null)
//            return result.getN();
//        else
//            return 0;
//
//    }
//
//    private String getTodaysDate() {
//        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
//        LocalDate localDate = LocalDate.now();
//        return dtf.format(localDate);
//    }
//
//    public Mongo mongo() throws Exception {
//
//        MongoClientOptions options = MongoClientOptions.builder()
//                .connectionsPerHost(100)
//                .connectTimeout(120000)
//                .socketTimeout(120000)
//                .maxWaitTime(1200000)
//                .threadsAllowedToBlockForConnectionMultiplier(1500)
//                .writeConcern(WriteConcern.ACKNOWLEDGED)
//                .build();
//
//        MongoClient client = new MongoClient(new ServerAddress("127.0.0.1", 27017), options);
//
//        return client;
//    }
//}
