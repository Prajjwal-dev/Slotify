package com.slotify.backend.service;

import com.slotify.backend.config.JWTUtil;
import com.slotify.backend.dto.AuthResponse;
import com.slotify.backend.dto.LoginRequest;
import com.slotify.backend.dto.RegisterRequest;
import com.slotify.backend.entity.Profile;
import com.slotify.backend.repository.ProfileRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AuthService {

    private static final Logger logger = LoggerFactory.getLogger(AuthService.class);

    private final ProfileRepository profileRepository;
    private final PasswordEncoder passwordEncoder;
    private final JWTUtil jwtUtil;

    public AuthResponse register(RegisterRequest request) {

        if (profileRepository.findByEmail(request.getEmail()).isPresent()) {
            logger.warn("Register failed: email already exists - {}", request.getEmail());
            throw new RuntimeException("Email already registered");
        }

        Profile profile = new Profile();
        profile.setId(UUID.randomUUID());
        profile.setFullName(request.getFullName());
        profile.setEmail(request.getEmail());
        profile.setPassword(passwordEncoder.encode(request.getPassword()));
        profile.setRole(request.getRole());
        profile.setCreatedAt(OffsetDateTime.now());

        profileRepository.save(profile);

        logger.info("Auth successful (REGISTER) - {}", request.getEmail());

        String token = jwtUtil.generateToken(profile.getEmail(), profile.getRole().name());
        return new AuthResponse(token, profile.getRole());
    }

    public AuthResponse login(LoginRequest request) {

        Profile profile = profileRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> {
                    logger.warn("Login failed: email not found - {}", request.getEmail());
                    return new RuntimeException("Email not found!");
                });

        if (!passwordEncoder.matches(request.getPassword(), profile.getPassword())) {
            logger.warn("Login failed: wrong password - {}", request.getEmail());
            throw new RuntimeException("Wrong Password!");
        }

        logger.info("Auth successful (LOGIN) - {}", request.getEmail());

        String token = jwtUtil.generateToken(profile.getEmail(), profile.getRole().name());
        return new AuthResponse(token, profile.getRole());
    }
}