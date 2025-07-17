package com.femi.courseservice.client;

import com.femi.courseservice.dto.UserDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "user-service")
public interface UserClient {

    @GetMapping("/api/v1/users/{id}")
    UserDto getUserById(@PathVariable("id") Long id);

    @GetMapping("/api/v1/user/email/{email}")
    UserDto getUserByEmail(@PathVariable String email);

}
