package com.controller

import com.db.cb.CbPersistanceServiceImpl
import com.db.mongo.MongoPersistanceServiceImpl
import com.db.PersistanceService
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component

@Component
class PersistanceServiceFactory(val couchbase: CbPersistanceServiceImpl,
                                val mongo:MongoPersistanceServiceImpl,
                                @Value("\${application.databaseName.name}") private val db: String) {

    fun getPersistenceService() : PersistanceService {

        if("mongo".equals(db))
            return mongo
        else
            return couchbase
    }
}