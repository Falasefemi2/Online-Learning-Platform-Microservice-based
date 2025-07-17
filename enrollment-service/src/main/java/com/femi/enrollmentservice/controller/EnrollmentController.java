package com.femi.enrollmentservice.controller;

import com.femi.enrollmentservice.dto.CourseResponse;
import com.femi.enrollmentservice.dto.EnrolledCourseResponse;
import com.femi.enrollmentservice.dto.EnrollmentRequest;
import com.femi.enrollmentservice.service.EnrollmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/enrollments")
@RequiredArgsConstructor
public class EnrollmentController {

    private final EnrollmentService enrollmentService;

    @PostMapping
    public ResponseEntity<String> enroll(@RequestBody EnrollmentRequest request,
                                         Authentication authentication) {
        enrollmentService.enroll(request.getCourseId(), authentication);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body("Enrollment successful");
    }

    @GetMapping
    public ResponseEntity<List<EnrolledCourseResponse>> myEnrollments(Authentication auth) {
        return ResponseEntity.ok(enrollmentService.getMyEnrollments(auth));
    }

    @GetMapping("/my-courses")
    public ResponseEntity<List<EnrolledCourseResponse>> getMyCourses(Authentication auth) {
        return ResponseEntity.ok(enrollmentService.getMyEnrollments(auth));
    }
}
