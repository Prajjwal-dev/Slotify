package com.slotify.backend.repository;

import com.slotify.backend.entity.BlockedDates;
import com.slotify.backend.entity.ProviderProfile;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface BlockedDatesRepository extends JpaRepository<BlockedDates, UUID> {
    Optional<BlockedDates> findByProviderAndBlockedDate(ProviderProfile provider, LocalDate blockedDate);

    List<BlockedDates> findByProvider(ProviderProfile provider);
}