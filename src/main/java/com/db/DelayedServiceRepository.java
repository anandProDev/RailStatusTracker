package com.db;

import com.domain.DelayedService;
import com.domain.DelayedServiceHolder;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface DelayedServiceRepository extends MongoRepository<DelayedServiceHolder, String> {

    DelayedService findByDate(String date);
}