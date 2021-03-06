package com.evolution.ddasoop.web.dto;


import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class TopCourseDto {

    private Long course_idx;
    private String course_intro;
    private String course_name;
    private int course_heart;
    private String course_img;

}
