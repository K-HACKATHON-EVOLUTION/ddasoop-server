package com.evolution.ddasoop.service;

import com.evolution.ddasoop.domain.Course;
import com.evolution.ddasoop.domain.CourseRepository;
import com.evolution.ddasoop.web.dto.CourseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class CourseService {

    private final CourseRepository courseRepository;

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


}
