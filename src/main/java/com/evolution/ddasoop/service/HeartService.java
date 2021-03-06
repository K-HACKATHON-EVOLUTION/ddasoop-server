package com.evolution.ddasoop.service;

import com.evolution.ddasoop.domain.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class HeartService {
    private final HeartRepository heartRepository;
    private final UserRepository userRepository;
    private final CourseRepository courseRepository;

    @Transactional
    public String changeHeart(Long courseIdx, String userIdx) throws IllegalArgumentException{
        Course course = courseRepository.findCourseByCourseIdxAndDeleteFlagFalse(courseIdx);
        User user = userRepository.findByUserIdxAndDeleteFlagFalse(userIdx);

        if((course == null)||(user == null)){
            throw new IllegalArgumentException();
        }

        Heart heart = heartRepository.findHeartByCourseCourseIdxAndUserUserIdx(courseIdx,userIdx);

        if(heart == null){
            heartRepository.save(Heart.builder()
                    .course(course)
                    .user(user)
                    .deleteFlag(false)
                    .build());
        }else{
            heart.updateDeleteFlag();
        }

        return "success";
    }
}
