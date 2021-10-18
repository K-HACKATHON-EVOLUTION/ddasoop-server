package com.evolution.ddasoop.domain;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CourseRepository extends JpaRepository<Course,Long> {
    Course findCourseByCourseIdx(Long course_idx);
    Course findCourseByCourseIdxAndDeleteFlagFalse(Long course_idx);
    List<Course> findAllByThemeAndDeleteFlagFalseOrderByCourseDate(Long theme);
    List<Course> findAllByDeleteFlagIsFalseOrderByCourseDate();
    List<Course> findAllByDeleteFlagIsFalse();
}
