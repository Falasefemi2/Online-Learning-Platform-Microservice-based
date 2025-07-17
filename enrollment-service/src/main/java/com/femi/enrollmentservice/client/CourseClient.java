package com.femi.enrollmentservice.client;

import com.femi.enrollmentservice.config.FeignClientConfig;
import com.femi.enrollmentservice.dto.CourseResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(
        name = "course-service",
        configuration = FeignClientConfig.class
)
public interface CourseClient {
    @GetMapping("/api/v1/courses/{id}")
    CourseResponse getCourseById(@PathVariable Long id);
}
