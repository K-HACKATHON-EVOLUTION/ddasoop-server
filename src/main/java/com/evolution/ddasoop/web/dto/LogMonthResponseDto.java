package com.evolution.ddasoop.web.dto;

import com.evolution.ddasoop.domain.Log;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@Builder
public class LogMonthResponseDto {
    private String userIdx;
    private Integer logCnt;
    private Double treeAmount;
    private List<LocalDate> logDates;
}
