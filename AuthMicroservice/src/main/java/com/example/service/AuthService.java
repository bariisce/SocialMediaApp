package com.example.service;

import com.example.dto.request.CreateUserRequestDto;
import com.example.dto.request.RegisterRequestDto;
import com.example.dto.response.LoginResponseDto;
import com.example.entity.Auth;
import com.example.manager.UserProfileManager;
import com.example.repository.AuthRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final AuthRepository authRepository;
    private final UserProfileManager userProfileManager;

    public Auth register(RegisterRequestDto dto) {
        Auth auth = authRepository.save(Auth.builder()
                .username(dto.getUsername())
                .email(dto.getEmail())
                .password(dto.getPassword())
                .build());
        userProfileManager.createUser(CreateUserRequestDto.builder()
                        .authId(auth.getId())
                        .username(auth.getUsername())
                        .email(auth.getEmail())
                .build());
        return auth;
    }

    public Boolean login(LoginResponseDto dto) {
        return authRepository.existsByUsernameAndPassword(dto.getUsername(), dto.getPassword());
    }
}
