package com.femi.enrollmentservice.service;

import com.femi.enrollmentservice.client.CourseClient;
import com.femi.enrollmentservice.dto.*;
import com.femi.enrollmentservice.model.Enrollment;
import com.femi.enrollmentservice.repository.EnrollmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EnrollmentService {

    private final EnrollmentRepository enrollmentRepository;
    private final CourseClient courseClient;

    public void enroll(Long courseId, Authentication auth) {
        AuthenticatedUser user = (AuthenticatedUser) auth.getPrincipal();

        if (user.getRole() != Role.USER) {
            throw new AccessDeniedException("Only users can enroll.");
        }

        CourseResponse course = courseClient.getCourseById(courseId);
        if (course.getStatus() != CourseStatus.APPROVED) {
            throw new IllegalArgumentException("Only approved courses can be enrolled.");
        }

        if (enrollmentRepository.existsByUserIdAndCourseId(user.getId(), courseId)) {
            throw new IllegalStateException("Already enrolled.");
        }

        Enrollment enrollment = Enrollment.builder()
                .userId(user.getId())
                .courseId(courseId)
                .build();

        enrollmentRepository.save(enrollment);
    }

    public List<EnrolledCourseResponse> getMyEnrollments(Authentication authentication) {
        AuthenticatedUser user = (AuthenticatedUser) authentication.getPrincipal();

        List<Enrollment> enrollments = enrollmentRepository.findByUserId(user.getId());

        return enrollments.stream()
                .map(enrollment -> {
                    CourseResponse course = courseClient.getCourseById(enrollment.getCourseId());
                    return EnrolledCourseResponse.builder()
                            .courseId(course.getId())
                            .title(course.getTitle())
                            .description(course.getDescription())
                            .build();
                })
                .toList();
    }


    private Long extractUserIdFromJwt() {
        // Use SecurityContextHolder and custom JwtUtil (or parse from token)
        // TEMP: just return a mock userId or pull from claims if you added that earlier
        return 1L;
    }
}
