package com.femi.courseservice.repository;

import com.femi.courseservice.model.Course;
import com.femi.courseservice.model.CourseStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;

public interface CourseRepository extends JpaRepository<Course, Long> {
    List<Course> findByInstructorId(Long instructorId);
//    List<Course> findByApproved(boolean approved);

    List<Course> findByStatus(CourseStatus status);
}