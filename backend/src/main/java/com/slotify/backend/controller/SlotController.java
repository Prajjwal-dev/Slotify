package com.slotify.backend.controller;

import com.slotify.backend.dto.SlotResponse;
import com.slotify.backend.service.SlotService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class SlotController {

    private final SlotService slotService;

    @GetMapping("/slots")
    public ResponseEntity<List<SlotResponse>> getSlots(@RequestParam UUID serviceId, @RequestParam LocalDate date) {
        return ResponseEntity.ok(slotService.getAvailableSlots(serviceId, date));
    }

}
