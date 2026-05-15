package com.slotify.backend.service;

import com.slotify.backend.dto.ServiceRequest;
import com.slotify.backend.entity.Profile;
import com.slotify.backend.entity.ProviderProfile;
import com.slotify.backend.entity.ServiceEntity;
import com.slotify.backend.repository.ProviderProfileRepository;
import com.slotify.backend.repository.ServiceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ServiceService {

    private final ProviderProfileRepository providerProfileRepository;

    private final ServiceRepository serviceRepository;

    public ServiceEntity createService(ServiceRequest request) {
        ProviderProfile provider = providerProfileRepository.findById(request.getProviderId()).orElseThrow(() -> new RuntimeException("Provider Id not found"));

        ServiceEntity service = new ServiceEntity();
        service.setProvider(provider);
        service.setName(request.getName());
        service.setPrice(request.getPrice());
        service.setDurationMinutes(request.getDurationMinutes());
        service.setDescription(request.getDescription());

        return serviceRepository.save(service);

    }

    public List<ServiceEntity> getServiceByProvider(UUID providerId) {
        ProviderProfile provider = providerProfileRepository.findById(providerId).orElseThrow(() -> new RuntimeException("Provider Id not found!"));

        return serviceRepository.findByProvider(provider);
    }

    public void deleteService(UUID serviceId) {
        serviceRepository.deleteById(serviceId);
        }
}
