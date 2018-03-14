package com.db;

import com.domain.DelayedService;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface DelayedServiceRepository extends MongoRepository<DelayedService, Long> {

    DelayedService findByDate(String date);
}