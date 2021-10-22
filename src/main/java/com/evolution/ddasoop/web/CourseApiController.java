package com.evolution.ddasoop.web;


import com.evolution.ddasoop.domain.Course;
import com.evolution.ddasoop.service.CourseService;
import com.evolution.ddasoop.web.dto.CourseDto;
import com.evolution.ddasoop.web.dto.TopCourseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Month;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/api")
public class CourseApiController {

    private final CourseService courseService;

    // 1. 월간코스 TOP 3 반환(이번 달에 추가된 좋아요 개수 기준 내림차순 정렬)
    @GetMapping("/course/top3")
    public List<TopCourseDto> monthTop3(){
        return courseService.getTop3Course();
    }

    // 4. 코스 하나 반환
    //location String 구분 수정 필요
    @GetMapping("/course/{course_idx}")
    public CourseDto getaCourse(@PathVariable("course_idx") Long course_idx){
        return courseService.getAcourse(course_idx);
    }

    //전체 코스 = 0, 내가 저장한 코스 =1 , 테마별 코스 2~5
    @GetMapping("/users/{userIdx}/courses/{select}")
    public List<TopCourseDto> selectMenu(@PathVariable("userIdx") String userIdx,
                                        @PathVariable("select") Long select){
        long theme = 0;
        if(select == 0 ){
            //2. 전체코스 보기
            return courseService.getAllCourse();
        }else if(select == 1){
            //내가 저장한 코스
            return courseService.getHeartedCourse(userIdx);
        }else if(select == 2){
            //3.테마별: 한강&하천
            theme = 0;
            return courseService.getThemeCourse(theme);
        }else if(select == 3){
            //공원
            theme = 1;
            return courseService.getThemeCourse(theme);
        }else if(select ==4){
            //도심
            theme = 2;
            return courseService.getThemeCourse(theme);
        }else if(select == 5){
            //문화재
            theme = 3;
            return courseService.getThemeCourse(theme);
        }else return null;
    }

}
