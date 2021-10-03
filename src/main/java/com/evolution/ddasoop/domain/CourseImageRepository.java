package com.evolution.ddasoop.domain;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseImageRepository extends JpaRepository<CourseImage, Long> {
    CourseImage findCourseImageByCourse(Course course);
}
