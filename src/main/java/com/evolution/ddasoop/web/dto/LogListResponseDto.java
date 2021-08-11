package com.evolution.ddasoop.web.dto;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@Builder
public class LogListResponseDto {
    private LocalDate logDate;
    private Integer dayOfWeek;
    private Long hours;
    private Long minutes;
    private Double carbon;
}
