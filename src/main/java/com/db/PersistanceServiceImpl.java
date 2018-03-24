package com.db;

import com.couchbase.client.core.BackpressureException;
import com.couchbase.client.java.Bucket;
import com.couchbase.client.java.Cluster;
import com.couchbase.client.java.CouchbaseCluster;
import com.couchbase.client.java.ReplicateTo;
import com.couchbase.client.java.document.AbstractDocument;
import com.couchbase.client.java.document.JsonDocument;
import com.couchbase.client.java.document.RawJsonDocument;
import com.couchbase.client.java.env.DefaultCouchbaseEnvironment;
import com.domain.DelayedService;
import com.domain.DelayedServiceHolder;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import rx.Observable;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;


@Repository
public class PersistanceServiceImpl implements PersistanceService {


    private final JsonDocument emptyDocument = JsonDocument.create("default");
    private final List<String> seedNodes;
    private final String bucketName;
    private final String bucketPassword;
    private final String replicas;
    private final ObjectMapper objectMapper;
    private Bucket bucket;
    private ReplicateTo replicateTo;
    private final static int COUCHBASE_OPERATION_TIME = 2000;
    private long readTimeout = 3000;

    @Autowired
    public PersistanceServiceImpl(        @Value("#{'${couchbase.seed.nodes}'.split(',')}") List<String> seedNodes,
                                          @Value("${couchbase.bucket.name}") String bucketName,
                                          @Value("${couchbase.bucket.pw}") String bucketPassword,
                                          @Value("${couchbase.replicas}") String replicas,
                                          ObjectMapper objectMapper
    ) throws Exception {
        this.seedNodes = seedNodes;
        this.bucketName = bucketName;
        this.bucketPassword = bucketPassword;
        this.replicas = replicas;
        this.objectMapper = objectMapper;
        try {

            Cluster cluster = CouchbaseCluster.create(DefaultCouchbaseEnvironment.builder().connectTimeout(10000).build(), seedNodes);
            this.bucket = cluster.openBucket(bucketName, bucketPassword);
            this.replicateTo = ReplicateTo.valueOf(replicas);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public <T> T get(String id, Class<T> c) {
        T result = null;
        try {
            AbstractDocument document = bucket.get(id, RawJsonDocument.class, readTimeout, TimeUnit.MILLISECONDS);
            if (document != null) {
                result = unmarshallDocument(c, document);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    private <T> T unmarshallDocument(Class<T> type, AbstractDocument document) {

        try {
            String json = document.content().toString();
            return objectMapper.readValue(json, type);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


    @Override
    public void updateRailDetails(DelayedServiceHolder delayedServiceHolder) {

        DelayedServiceHolder fromCB = get(delayedServiceHolder.getDate(), DelayedServiceHolder.class);

        if(fromCB == null){
            insert(delayedServiceHolder.getDate(),delayedServiceHolder);
        } else {
            update(fromCB, delayedServiceHolder);
            replace(fromCB.getDate(), fromCB);
        }
    }

    private void update(DelayedServiceHolder fromCB, DelayedServiceHolder delayedServiceHolder) {

        Map<String, List<DelayedService>> delayedServices = delayedServiceHolder.getDelayedServices();

        delayedServices.forEach((k,v) -> add(fromCB, v.get(0)));
    }


    private void add(DelayedServiceHolder holder, DelayedService delayedService){

        List<DelayedService> delayedServices = holder.getDelayedServices().get(delayedService.getTrainNumber());

        if(delayedServices!=null && delayedServices.isEmpty()){
            holder.getDelayedServices().put(delayedService.getTrainNumber(), delayedServices);
            return;
        } else {
            delayedServices = new ArrayList<>();
            delayedServices.add(delayedService);
            holder.getDelayedServices().put(delayedService.getTrainNumber(), delayedServices);
        }
    }


    private void insert(final String id, Object object) {
        try {
            RawJsonDocument document = RawJsonDocument.create(id, objectMapper.writeValueAsString(object));
            //final Observable<RawJsonDocument> obsDoc = bucket.async().insert(document);

            bucket.insert(document);

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private void replace(final String id, Object object) {
        try {
            RawJsonDocument document = RawJsonDocument.create(id, objectMapper.writeValueAsString(object));
            bucket.replace(document);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
