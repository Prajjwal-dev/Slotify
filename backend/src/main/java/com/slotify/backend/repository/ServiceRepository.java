package com.slotify.backend.repository;

import com.slotify.backend.entity.ProviderProfile;
import com.slotify.backend.entity.ServiceEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface ServiceRepository extends JpaRepository<ServiceEntity, UUID> {

    List<ServiceEntity> findByProvider(ProviderProfile provider);
}