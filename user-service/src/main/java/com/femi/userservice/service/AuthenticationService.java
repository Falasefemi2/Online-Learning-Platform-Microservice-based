package com.femi.userservice.service;

import com.femi.userservice.config.JwtService;
import com.femi.userservice.dto.LoginRequest;
import com.femi.userservice.dto.LoginResponse;
import com.femi.userservice.dto.RegisterRequest;
import com.femi.userservice.model.Role;
import com.femi.userservice.model.User;
import com.femi.userservice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public LoginResponse register(RegisterRequest registerRequest) {

        if(registerRequest.getRole() == Role.ADMIN) {
            throw new IllegalArgumentException("You cannot register as ADMIN");
        }

        var user = User.builder()
                .firstName(registerRequest.getFirstName())
                .lastName(registerRequest.getLastName())
                .email(registerRequest.getEmail())
                .password(passwordEncoder.encode(registerRequest.getPassword()))
                .role(registerRequest.getRole())
                .build();
        userRepository.save(user);
        var jwtToken = jwtService.generateToken(user);
        return LoginResponse.builder()
                .token(jwtToken)
                .email(user.getEmail())
                .role(user.getRole().name())
                .build();
    }

    public LoginResponse login(LoginRequest loginRequest) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getEmail(),
                        loginRequest.getPassword()
                )
        );
        var user = userRepository.findUserByEmail(loginRequest.getEmail())
                .orElseThrow(() -> new UsernameNotFoundException(loginRequest.getEmail()));
        var jwtToken = jwtService.generateToken(user);
        return LoginResponse.builder()
                .token(jwtToken)
                .email(user.getEmail())
                .role(user.getRole().name())
                .build();
    }
}
