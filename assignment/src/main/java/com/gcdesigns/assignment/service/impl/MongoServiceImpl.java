package com.gcdesigns.assignment.service.impl;

import com.gcdesigns.assignment.dto.AggregatedResult;
import com.gcdesigns.assignment.service.IMongoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.GroupOperation;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MongoServiceImpl implements IMongoService {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public List<AggregatedResult> getAggregation() {
        GroupOperation  group = Aggregation.group("lenderId", "interestPerDay", "customerId")
                .sum("remainingAmount").as("totalRemainingAmount")
                .sum("interestPerDay").as("totalInterest")
                .sum("penaltyPerDay").as("totalPenalty");
        Aggregation aggregation = Aggregation.newAggregation(group);

        return mongoTemplate.aggregate(aggregation, "assignment", AggregatedResult.class).getMappedResults();
    }
}
