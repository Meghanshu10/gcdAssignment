package com.gcdesigns.assignment.service;

import com.gcdesigns.assignment.dto.AggregatedResult;

import java.util.List;


public interface IMongoService {
    List<AggregatedResult> getAggregation();
}
