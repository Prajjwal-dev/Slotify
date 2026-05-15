package com.slotify.backend.controller;

import com.slotify.backend.dto.ProviderProfileRequest;
import com.slotify.backend.entity.ProviderProfile;
import com.slotify.backend.service.ProviderService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/provider")
@RequiredArgsConstructor
public class ProviderController {

    private final ProviderService providerService;

    @PostMapping("/profile")
    public ResponseEntity<ProviderProfile> createProviderProfile(@RequestBody ProviderProfileRequest request) {
        return ResponseEntity.ok(providerService.createProfile(request));
    }

    @GetMapping("/profile/{userId}")
    public ResponseEntity<ProviderProfile> getProviderProfile(@PathVariable UUID userId) {
        return ResponseEntity.ok(providerService.getProfile(userId));
    }
}
