package com.evolution.ddasoop.web.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
public class CourseDto {

    private String course_name;
    private String course_intro;
    private Double distance;
    private String location;

    @Builder
    public CourseDto(String course_name, String course_intro, Double distance, String location) {
        this.course_name = course_name;
        this.course_intro = course_intro;
        this.distance = distance;
        this.location = location;
    }
}
