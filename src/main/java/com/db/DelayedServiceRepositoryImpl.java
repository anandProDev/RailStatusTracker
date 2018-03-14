package com.db;

import com.model.RailDetail;
import com.mongodb.WriteResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Component
public class DelayedServiceRepositoryImpl implements DelayedServiceRepositoryCustom {

    @Autowired
    MongoTemplate mongoTemplate;

    @Override
    public int updateRailDetails(RailDetail railDetail){

        Query query = new Query(Criteria.where("date").is(getTodaysDate()));
        Update update = new Update();
        update.set("displayAds", railDetail);

        WriteResult result = mongoTemplate.updateFirst(query, update, RailDetail.class);

        if(result!=null)
            return result.getN();
        else
            return 0;

    }

    private String getTodaysDate() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        LocalDate localDate = LocalDate.now();
        return dtf.format(localDate);
    }
}
