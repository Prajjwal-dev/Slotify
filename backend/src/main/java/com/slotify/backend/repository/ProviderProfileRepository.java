package com.slotify.backend.repository;

import com.slotify.backend.entity.Profile;
import com.slotify.backend.entity.ProviderProfile;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface ProviderProfileRepository extends JpaRepository<ProviderProfile, UUID> {

    Optional<ProviderProfile> findByUser(Profile user);
    Optional<ProviderProfile> findById(UUID id);
}