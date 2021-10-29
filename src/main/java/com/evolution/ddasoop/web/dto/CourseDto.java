package com.evolution.ddasoop.web.dto;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
public class CourseDto {



    private String course_name;
    private String course_intro;
    private Double distance;
    private List location;
    private Integer heart;
    private List location_name;

    @Builder
    public CourseDto(String course_name, String course_intro, Double distance, List location, Integer heart, List location_name) {
        this.course_name = course_name;
        this.course_intro = course_intro;
        this.distance = distance;
        this.location = location;
        this.heart = heart;
        this.location_name = location_name;
    }
}
