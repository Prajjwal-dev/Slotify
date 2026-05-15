package com.slotify.backend.controller;

import com.slotify.backend.dto.AvailabilityRequest;
import com.slotify.backend.dto.BlockedDateRequest;
import com.slotify.backend.entity.Availability;
import com.slotify.backend.entity.BlockedDates;
import com.slotify.backend.service.AvailabilityService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class AvailabilityController {

    private final AvailabilityService availabilityService;

    @PostMapping("/availability")
    public ResponseEntity<Availability> setAvailability(@RequestBody AvailabilityRequest request) {
        return ResponseEntity.ok(availabilityService.setAvailability(request));
    }

    @GetMapping("/availability/provider/{providerId}")
    public ResponseEntity<List<Availability>> getAvailabilityByProvider(@PathVariable UUID providerId) {
        return ResponseEntity.ok(availabilityService.getAvailability(providerId));
    }

    @PostMapping("/availability/block")
    public ResponseEntity<BlockedDates> blockDate(@RequestBody BlockedDateRequest request) {
        return ResponseEntity.ok(availabilityService.blockDates(request));
    }
}

