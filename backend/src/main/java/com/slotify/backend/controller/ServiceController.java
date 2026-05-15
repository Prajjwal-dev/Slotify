package com.slotify.backend.controller;

import com.slotify.backend.dto.ServiceRequest;
import com.slotify.backend.entity.ServiceEntity;
import com.slotify.backend.service.ServiceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Provider;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ServiceController {

    private final ServiceService service;

    @PostMapping("/services")
    public ResponseEntity<ServiceEntity> createService(@RequestBody ServiceRequest request) {
        return ResponseEntity.ok(service.createService(request));
    }

    @GetMapping("/services/provider/{providerId}")
    public ResponseEntity<List<ServiceEntity>> getServiceFromProvider(@PathVariable UUID providerId) {
        return ResponseEntity.ok(service.getServiceByProvider(providerId));
    }

    @DeleteMapping("/services/{serviceId}")
    public ResponseEntity<Void> deleteService(@PathVariable UUID serviceId) {
        service.deleteService(serviceId);

        return ResponseEntity.noContent().build();
    }

}
