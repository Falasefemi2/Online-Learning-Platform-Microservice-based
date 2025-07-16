package com.femi.courseserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class CourseServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(CourseServerApplication.class, args);
    }

}
