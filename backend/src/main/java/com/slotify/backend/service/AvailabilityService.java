package com.slotify.backend.service;

import com.slotify.backend.dto.AvailabilityRequest;
import com.slotify.backend.entity.Availability;
import com.slotify.backend.entity.Profile;
import com.slotify.backend.entity.ProviderProfile;
import com.slotify.backend.repository.AvailabilityRepository;
import com.slotify.backend.repository.ProviderProfileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AvailabilityService {

    private final ProviderProfileRepository providerProfileRepository;

    private final AvailabilityRepository availabilityRepository;

    public Availability setAvailability(AvailabilityRequest request) {
        ProviderProfile provider = providerProfileRepository.findById(request.getProviderId()).orElseThrow(() -> new RuntimeException("Provider Id not found!"));

        Availability availability = new Availability();
        availability.setProvider(provider);
        availability.setDayofWeek(request.getDayofWeek());
        availability.setStartTime(request.getStartTime());
        availability.setEndTime(request.getEndTime());

        return availabilityRepository.save(availability);
    }

    public List<Availability> getAvailability(UUID providerId) {
        ProviderProfile provider = providerProfileRepository.findById(providerId).orElseThrow(() -> new RuntimeException("Provider Id not found!"));

        return availabilityRepository.findByProvider(provider);
    }
}
