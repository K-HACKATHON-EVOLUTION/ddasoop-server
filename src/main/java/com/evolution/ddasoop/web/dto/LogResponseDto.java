package com.evolution.ddasoop.web.dto;

import com.evolution.ddasoop.domain.Log;
import lombok.Getter;

import java.time.Duration;
import java.time.LocalDateTime;

@Getter
public class LogResponseDto {
    private Long logIdx;
    private LocalDateTime logDateTime;
    private String startLocation;
    private String endLocation;
    private String route;
    private Long hours;
    private Long minutes;
    private Double distance;
    private Double carbon;

    public LogResponseDto(Log entity){
        this.logIdx = entity.getLogIdx();
        this.logDateTime = entity.getStartTime();
        this.startLocation = entity.getStartLocation();
        this.endLocation = entity.getEndLocation();
        this.route = entity.getRoute();

        Duration d = Duration.between(entity.getStartTime(), entity.getEndTime());
        this.hours = d.toHours();
        this.minutes = d.minusHours(hours).toMinutes();
        this.distance = entity.getDistance();
        this.carbon = entity.getCarbon();

    }
}
