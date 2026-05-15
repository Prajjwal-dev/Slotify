package com.slotify.backend.service;

import com.slotify.backend.dto.ProviderProfileRequest;
import com.slotify.backend.entity.Profile;
import com.slotify.backend.entity.ProviderProfile;
import com.slotify.backend.repository.ProfileRepository;
import com.slotify.backend.repository.ProviderProfileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProviderService {

    private final ProviderProfileRepository providerProfileRepository;
    private final ProfileRepository profileRepository;

    public ProviderProfile createProfile(ProviderProfileRequest request) {
        Profile profile = profileRepository.findById(request.getUserId()).orElseThrow(() -> new RuntimeException("User not found"));

        ProviderProfile providerProfile = new ProviderProfile();
        providerProfile.setUser(profile);
        providerProfile.setDescription(request.getDescription());
        providerProfile.setBusinessName(request.getBusinessName());

        return providerProfileRepository.save(providerProfile);
    }

    public ProviderProfile getProfile(UUID userId) {
        Profile profile = profileRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));

        return providerProfileRepository.findByUser(profile).orElseThrow(() -> new RuntimeException("Provider Profile not found"));
    }
}
