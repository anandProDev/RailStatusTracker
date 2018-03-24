//package com.db;
//
//import com.couchbase.client.core.BackpressureException;
//import com.couchbase.client.core.CouchbaseException;
//import com.couchbase.client.java.*;
//import com.couchbase.client.java.document.AbstractDocument;
//import com.couchbase.client.java.document.Document;
//import com.couchbase.client.java.document.JsonDocument;
//import com.couchbase.client.java.document.RawJsonDocument;
//import com.couchbase.client.java.env.DefaultCouchbaseEnvironment;
//import com.exception.DataAlreadyExistsException;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import org.omg.CORBA.portable.ApplicationException;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.stereotype.Repository;
//
//import java.util.List;
//import java.util.concurrent.TimeUnit;
//import java.util.concurrent.TimeoutException;
//
//@Repository
//public class CouchbaseDocumentStore implements DocumentStore {
//
//    private final JsonDocument emptyDocument = JsonDocument.create("default");
//    private final List<String> seedNodes;
//    private final String bucketName;
//    private final String bucketPassword;
//    private final String replicas;
//    private final Long maximumAllowedDocumentSizeValue;
//    private final ObjectMapper objectMapper;
//    private Bucket bucket;
//    private ReplicateTo replicateTo;
//    private final static int COUCHBASE_OPERATION_TIME = 2000;
//    private long readTimeout = 3000;
//
//    @Autowired
//    public CouchbaseDocumentStore(        @Value("#{'${couchbase.seed.nodes}'.split(',')}") List<String> seedNodes,
//                                          @Value("${couchbase.bucket.name}") String bucketName,
//                                          @Value("${couchbase.bucket.pw}") String bucketPassword,
//                                          @Value("${couchbase.replicas}") String replicas,
//                                          @Value("${max.proposition.document.size}") Long maximumAllowedDocumentSizeValue,
//                                                      ObjectMapper objectMapper
//    ) throws Exception {
//        this.seedNodes = seedNodes;
//        this.bucketName = bucketName;
//        this.bucketPassword = bucketPassword;
//        this.replicas = replicas;
//        this.maximumAllowedDocumentSizeValue = maximumAllowedDocumentSizeValue;
//        this.objectMapper = objectMapper;
//        try {
//
//            Cluster cluster = CouchbaseCluster.create(DefaultCouchbaseEnvironment.builder().connectTimeout(10000).build(), seedNodes);
//            this.bucket = cluster.openBucket(bucketName, bucketPassword);
//            this.replicateTo = ReplicateTo.valueOf(replicas);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    @Override
//    public <T> T get(String id, Class<T> c) {
//        T result = null;
//        boolean failoverToReplica = false;
//        try {
//            AbstractDocument document = bucket.get(id, RawJsonDocument.class, readTimeout, TimeUnit.MILLISECONDS);
//            if (document != null) {
//                result = unmarshallDocument(c, document);
//            }
//        } catch (IllegalArgumentException e) {
//            throw e;
//        }
//        catch (BackpressureException e) {
//        } catch (RuntimeException e) {
//        }
//        return result;
//    }
//
//
////    private <T> T getFromReplica(final String id, Class<T> c) {
////        final String docKey = documentKeyFactory.keyWithTenantPrefix(id);
////        try {
////            List<JsonDocument> replicaDocument = bucket.getFromReplica(docKey, ReplicaMode.FIRST);
////            if (!replicaDocument.isEmpty()) {
////                return unmarshallDocument(c, replicaDocument.get(0));
////            }
////        } catch (BackpressureException e) {
////            throw new ApplicationException(ErrorCode.COUCHBASE_CLIENT_QUEUE_FULL, String.format(GET_DOCUMENT_ERROR_MESSAGE, docKey, e.getMessage()), e);
////        } catch (RuntimeException e) {
////            throw new ApplicationException(ErrorCode.COUCHBASE_FAILED_TO_READ_FROM_REPLICA, String.format(GET_DOCUMENT_FROM_REPLICA_ERROR_MESSAGE, docKey, e.getMessage()), e.getCause());
////        }
////        return null;
////    }
//
//    private <T> T unmarshallDocument(Class<T> c, AbstractDocument document) {
//        String json = document.content().toString();
//        long cas = document.cas();
//        T unmarshall = objectMapper.unmarshall(json, c);
//        AnnotationHelper.setCasValue(unmarshall, cas);
//        return unmarshall;
//    }
//
//
//    public void delete(String id, PersistenceOptions persistenceOptions) {
//        final String docKey = documentKeyFactory.keyWithTenantPrefix(id);
//        Document doc = RawJsonDocument.create(docKey, "");
//        remove(docKey, doc, persistenceOptions, writeTimeout).waitForCompletion();
//    }
//
//    public void delete(String id, long cas, int opTimeout) {
//        final String docKey = documentKeyFactory.keyWithTenantPrefix(id);
//        Document doc = RawJsonDocument.create(docKey, "", cas);
//        remove(docKey, doc, PersistenceOptions.DEFAULT, opTimeout).waitForCompletion();
//    }
//
//    @Override
//    public long add(String id, Object object, int opTimeout) {
//        return 0;
//    }
//
//    @Override
//    public void delete(String id, long cas, int opTimeout) {
//
//    }
//
//    @Override
//    public void update(String id, Object object) {
//
//    }
//
//    @Override
//    public void create(String id, Object object) throws DataAlreadyExistsException {
//
//    }
//
//    @Override
//    public void delete(String id) {
//
//    }
//}
