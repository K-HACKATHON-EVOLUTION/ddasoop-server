package com.evolution.ddasoop.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HeartRepository extends JpaRepository<Heart, Long> {
    Heart findHeartByCourseCourseIdxAndUserUserIdxAndDeleteFlagFalse(Long courseIdx, String userIdx);
    Integer countHeartByCourseAndDeleteFlagFalse(Course course);
    List<Heart> findAllByUserUserIdxAndDeleteFlagFalse(String userIdx);
}
