package com.evolution.ddasoop.web.dto;

import com.evolution.ddasoop.domain.Log;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class LogRequestDto {
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private String startLocation;
    private String endLocation;
    private String route;
    private Double distance;

    @Builder
    public LogRequestDto(LocalDateTime startTime, LocalDateTime endTime, String startLocation, String endLocation, String route, Double distance){
        this.startTime = startTime;
        this.endTime = endTime;
        this.startLocation = startLocation;
        this.endLocation = endLocation;
        this.route = route;
        this.distance = distance;
    }
}
