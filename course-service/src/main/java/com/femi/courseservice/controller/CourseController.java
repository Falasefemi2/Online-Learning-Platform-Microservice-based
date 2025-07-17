package com.femi.courseservice.controller;

import com.femi.courseservice.client.UserClient;
import com.femi.courseservice.dto.CourseRequest;
import com.femi.courseservice.dto.CourseResponse;
import com.femi.courseservice.service.CourseService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/courses")
@RequiredArgsConstructor
public class CourseController {

    private final CourseService courseService;
    private final UserClient userClient;

    @PostMapping
    public ResponseEntity<CourseResponse> createCourse(@Valid @RequestBody CourseRequest request) {
        CourseResponse response = courseService.createCourse(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/approve/{id}")
    public ResponseEntity<CourseResponse> approveCourse(@PathVariable Long id) {
        CourseResponse response = courseService.approveCourse(id);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<List<CourseResponse>> getAllApprovedCourses() {
        return ResponseEntity.ok(courseService.getAllApprovedCourses());
    }

    @GetMapping("/pending")
    public ResponseEntity<List<CourseResponse>> getAllPendingCourses() {
        return ResponseEntity.ok(courseService.getAllPendingCourses());
    }

    @GetMapping("/my-courses")
    @PreAuthorize("hasRole('INSTRUCTOR')")
    public ResponseEntity<List<CourseResponse>> getMyCourses(Authentication authentication) {
        String email = authentication.getName(); // Extracted from JWT token
        List<CourseResponse> courses = courseService.getMyCourses(email);
        return ResponseEntity.ok(courses);
    }
}
