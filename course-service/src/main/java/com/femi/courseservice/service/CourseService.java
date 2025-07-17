package com.femi.courseservice.service;

import com.femi.courseservice.client.UserClient;
import com.femi.courseservice.dto.CourseRequest;
import com.femi.courseservice.dto.CourseResponse;
import com.femi.courseservice.dto.Role;
import com.femi.courseservice.model.Course;
import com.femi.courseservice.model.CourseStatus;
import com.femi.courseservice.repository.CourseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CourseService {

    private final CourseRepository courseRepository;
    private final UserClient userClient;

    public CourseResponse createCourse(CourseRequest courseRequest) {
        var user = userClient.getUserById(courseRequest.getInstructorId());

        if (!user.getRole().equals(Role.INSTRUCTOR)) {
            throw new RuntimeException("Only instructors can create courses.");
        }


        Course course = Course.builder()
                .title(courseRequest.getTitle())
                .description(courseRequest.getDescription())
                .instructorId(courseRequest.getInstructorId())
                .build();
        Course savedCourse = courseRepository.save(course);

        return CourseResponse.builder()
                .id(savedCourse.getId())
                .title(savedCourse.getTitle())
                .description(savedCourse.getDescription())
                .instructorId(savedCourse.getInstructorId())
                .status(savedCourse.getStatus())
                .createdAt(savedCourse.getCreatedAt())
                .updatedAt(savedCourse.getUpdatedAt())
                .build();
    }

    public CourseResponse approveCourse(Long courseId) {
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new RuntimeException("Course not found"));

        course.setStatus(CourseStatus.APPROVED);
        course.setUpdatedAt(LocalDateTime.now());
        Course updated = courseRepository.save(course);

        return CourseResponse.builder()
                .id(updated.getId())
                .title(updated.getTitle())
                .description(updated.getDescription())
                .instructorId(updated.getInstructorId())
                .status(updated.getStatus())
                .createdAt(updated.getCreatedAt())
                .updatedAt(updated.getUpdatedAt())
                .build();
    }

    public List<CourseResponse> getAllApprovedCourses() {
        return courseRepository.findByStatus(CourseStatus.APPROVED)
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    public List<CourseResponse> getAllPendingCourses() {
        return courseRepository.findByStatus(CourseStatus.PENDING)
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    public List<CourseResponse> getCoursesByInstructor(Long instructorId) {
        return courseRepository.findByInstructorId(instructorId)
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    public List<CourseResponse> getMyCourses(String email) {
        var user = userClient.getUserByEmail(email);

        if (!user.getRole().equals(Role.INSTRUCTOR)) {
            throw new RuntimeException("Only instructors can view their courses.");
        }

        return courseRepository.findByInstructorId(user.getId())
                .stream()
                .map(this::mapToResponse)
                .toList();
    }



    private CourseResponse mapToResponse(Course course) {
        return CourseResponse.builder()
                .id(course.getId())
                .title(course.getTitle())
                .description(course.getDescription())
                .instructorId(course.getInstructorId())
                .status(course.getStatus())
                .createdAt(course.getCreatedAt())
                .updatedAt(course.getUpdatedAt())
                .build();
    }
}
