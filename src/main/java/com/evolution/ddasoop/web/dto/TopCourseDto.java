package com.evolution.ddasoop.web.dto;


import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class TopCourseDto {

    private String course_intro;
    private String course_name;
    private int heart;
    private String course_img;

}
