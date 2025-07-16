package com.femi.userservice.config;

import com.femi.userservice.model.Role;
import com.femi.userservice.model.User;
import com.femi.userservice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@RequiredArgsConstructor
public class AdminUserInitializer {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Bean
    public CommandLineRunner createAdminUser() {
        return args -> {
            String adminEmail = "femifalase228@gmail.com";

            if(userRepository.findUserByEmail(adminEmail).isEmpty()) {
                User admin = User.builder()
                        .firstName("Femi")
                        .lastName("Falase")
                        .email(adminEmail)
                        .password(passwordEncoder.encode("Falasefemi228@"))
                        .role(Role.ADMIN)
                        .build();
                userRepository.save(admin);
                System.out.println("✅ Admin user created: " + adminEmail);
            }
        };
    }
}
