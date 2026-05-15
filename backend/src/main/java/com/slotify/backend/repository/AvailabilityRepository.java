package com.slotify.backend.repository;

import com.slotify.backend.entity.Availability;
import com.slotify.backend.entity.ProviderProfile;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface AvailabilityRepository extends JpaRepository<Availability, UUID> {
    List<Availability> findByProvider(ProviderProfile providerProfile);
}