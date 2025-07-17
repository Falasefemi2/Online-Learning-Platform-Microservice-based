package com.femi.enrollmentservice.client;

import com.femi.enrollmentservice.dto.UserDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "user-service")
public interface UserClient {
    @GetMapping("/api/v1/auth/users/email/{email}")
    UserDto getUserByEmail(@PathVariable("email") String email);
}
