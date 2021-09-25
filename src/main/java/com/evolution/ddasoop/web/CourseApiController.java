package com.evolution.ddasoop.web;


import com.evolution.ddasoop.domain.Course;
import com.evolution.ddasoop.service.CourseService;
import com.evolution.ddasoop.web.dto.CourseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/api")
public class CourseApiController {

    private final CourseService courseService;

    // 1. 월간코스 TOP 3 반환(이번 달에 추가된 좋아요 개수 기준 내림차순 정렬)
    @GetMapping("/courses/top3")
    public void monthTop3(){

    }

    // 2. 전체 코스 반환(최신순)
    @GetMapping("/courses")
    public void getCourseList(){

    }

    // 3. 테마별 코스 반환(최신순)
    @GetMapping("/course/theme")
    public void getThemeCourseList(){

    }

    // 4. 코스 하나 반환
    //location String 구분 수정 필요
    @GetMapping("/courses/{course_idx}")
    public CourseDto getaCourse(@PathVariable("course_idx") Long course_idx){
        return courseService.getAcourse(course_idx);
    }


}
