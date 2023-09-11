package com.gcdesigns.assignment.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AggregatedResult {
    private int totalRemainingAmount;
    private int totalInterest;
    private Double totalPenalty;
}