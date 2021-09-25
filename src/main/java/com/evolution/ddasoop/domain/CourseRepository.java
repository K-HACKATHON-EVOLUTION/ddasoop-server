package com.evolution.ddasoop.domain;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course,Long> {
    Course findCourseByCourseIdx(Long course_idx);
    Course findCourseByCourseIdxAndDeleteFlagFalse(Long course_idx);
}
