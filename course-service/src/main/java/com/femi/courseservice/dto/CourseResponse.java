package com.femi.courseservice.dto;

import com.femi.courseservice.model.CourseStatus;
import lombok.*;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CourseResponse {

    private Long id;
    private String title;
    private String description;
    private Long instructorId;

    private CourseStatus status;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}