package com.gcdesigns.assignment.service;

import com.gcdesigns.assignment.dto.AggregatedResult;
import com.gcdesigns.assignment.service.impl.MongoServiceImpl;
import org.bson.Document;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.aggregation.GroupOperation;

import java.util.ArrayList;
import java.util.List;

@ExtendWith(MockitoExtension.class)
public class MongoServiceImplTest {

    @Mock
    MongoTemplate mongoTemplate;

    @InjectMocks
    MongoServiceImpl mongoService;

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
    }


    @Test
    public void testGetAggregation(){

        AggregationResults<AggregatedResult> results = Mockito.mock(AggregationResults.class);

        GroupOperation group = Aggregation.group("lenderId", "interestPerDay", "customerId")
                .sum("remainingAmount").as("totalRemainingAmount")
                .sum("interestPerDay").as("totalInterest")
                .sum("penaltyPerDay").as("totalPenalty");

        Mockito.doReturn(results)
                .when(mongoTemplate)
                .aggregate(Mockito.nullable(Aggregation.class), Mockito.nullable(String.class), Mockito.<Class<?>> any());
        Assertions.assertEquals(mongoService.getAggregation(),results.getMappedResults());
     }

}
