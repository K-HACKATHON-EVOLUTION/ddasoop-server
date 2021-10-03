package com.evolution.ddasoop.service;

import com.evolution.ddasoop.domain.*;
import com.evolution.ddasoop.web.dto.CourseDto;
import com.evolution.ddasoop.web.dto.TopCourseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;


@RequiredArgsConstructor
@Service
public class CourseService {

    private final CourseRepository courseRepository;
    private final CourseImageRepository courseImageRepository;
    private final HeartRepository heartRepository;

    @Transactional
    public CourseDto getAcourse(Long course_idx){
        Course course = courseRepository.findCourseByCourseIdxAndDeleteFlagFalse(course_idx);
        if(course !=null){
            CourseDto courseDto = CourseDto.builder()
                    .course_name(course.getCourseName())
                    .course_intro(course.getCourseIntro())
                    .distance(course.getDistance())
                    .location(course.getLocation())
                    .build();
            return courseDto;
        }else return CourseDto.builder().build();
    }

    @Transactional
    public List<TopCourseDto> getThemeCourse(String theme){
        List <TopCourseDto> topCourseDtoList = new ArrayList<>();
            for(Course course : courseRepository.findAllByThemeAndDeleteFlagFalseOrderByCourseDate(theme)){
                CourseImage courseImage = courseImageRepository.findCourseImageByCourse(course);
                topCourseDtoList.add(TopCourseDto.builder()
                        .course_intro(course.getCourseIntro())
                        .course_name(course.getCourseName())
                        .course_img(courseImage.getFilePath())
                        .build());
            }

        return topCourseDtoList;
    }

    @Transactional
    public List<TopCourseDto> getAllCourse(){
        List<TopCourseDto> topCourseDtoList = new ArrayList<>();
        for(Course course: courseRepository.findAllByDeleteFlagIsFalseOrderByCourseDate()){
            CourseImage courseImage = courseImageRepository.findCourseImageByCourse(course);
            topCourseDtoList.add(TopCourseDto.builder()
                    .course_name(course.getCourseName())
                    .course_intro(course.getCourseIntro())
                    .course_img(courseImage.getFilePath())
                    .build());

        }
        return topCourseDtoList;
    }

    @Transactional
    public List<TopCourseDto> getTop3Course(){
        List<TopCourseDto> topCourseDtoList = new ArrayList<>();
        //이번 달에 추가된 좋아요 개수 기준 내림차순 정렬
        return topCourseDtoList;
    }

}
