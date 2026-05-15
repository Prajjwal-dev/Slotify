package com.slotify.backend.controller;

import com.slotify.backend.dto.BookingRequest;
import com.slotify.backend.entity.Booking;
import com.slotify.backend.service.BookingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class BookingController {

    private final BookingService bookingService;

    @PostMapping("/bookings")
    public ResponseEntity<Booking> createBooking(@RequestBody BookingRequest request) {
        return ResponseEntity.ok(bookingService.createBooking(request));
    }

    @GetMapping("/bookings/customer/{customerId}")
    public ResponseEntity<List<Booking>> getBookingByCustomer(@PathVariable UUID customerId) {
        return ResponseEntity.ok(bookingService.getBookingsByCustomer(customerId));
    }

    @GetMapping("/bookings/provider/{providerId}")
    public ResponseEntity<List<Booking>> getBookingsByProvider(@PathVariable UUID providerId) {
        return ResponseEntity.ok(bookingService.getBookingsByProvider(providerId));
    }

    @PostMapping("/bookings/{bookingId}/cancel")
    public ResponseEntity<Booking> cancelBooking(@PathVariable UUID bookingId) {
        return ResponseEntity.ok(bookingService.cancelBooking(bookingId));
    }
}
