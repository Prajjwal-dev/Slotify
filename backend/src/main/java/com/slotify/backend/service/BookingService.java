package com.slotify.backend.service;

import com.slotify.backend.dto.BookingRequest;
import com.slotify.backend.dto.ProviderProfileRequest;
import com.slotify.backend.entity.*;
import com.slotify.backend.repository.BookingRepository;
import com.slotify.backend.repository.ProfileRepository;
import com.slotify.backend.repository.ProviderProfileRepository;
import com.slotify.backend.repository.ServiceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class BookingService {

    private final ProfileRepository profileRepository;

    private final ProviderProfileRepository providerProfileRepository;

    private final ServiceRepository serviceRepository;

    private final BookingRepository bookingRepository;

    public Booking createBooking(BookingRequest request) {
        Profile profile = profileRepository.findById(request.getCustomerId()).orElseThrow(() -> new RuntimeException("Customer Id not found!"));

        ProviderProfile provider = providerProfileRepository.findById(request.getProviderId()).orElseThrow(() -> new RuntimeException("Provider Id not found!"));

        ServiceEntity service = serviceRepository.findById(request.getServiceId()).orElseThrow(() -> new RuntimeException("Service Id not found!"));

        boolean hasConflict = bookingRepository.existsOverlappingBooking(provider, request.getStartTime(), request.getEndTime());

        if(hasConflict) {
            throw new RuntimeException("This slot is already booked");
        }

        Booking booking = new Booking();

        booking.setProvider(provider);
        booking.setService(service);
        booking.setCustomer(profile);
        booking.setStatus(BookingStatus.PENDING);
        booking.setCreatedAt(LocalDateTime.now());
        booking.setStartTime(request.getStartTime());
        booking.setEndTime(request.getEndTime());
        booking.setNotes(request.getNotes());

        return bookingRepository.save(booking);
    }

    public List<Booking> getBookingsByCustomer(UUID customerId) {
        Profile profile = profileRepository.findById(customerId).orElseThrow(() -> new RuntimeException("Customer Id not found!"));

        return bookingRepository.findByCustomer(profile);
    }

    public List<Booking> getBookingsByProvider(UUID providerId) {
        ProviderProfile provider = providerProfileRepository.findById(providerId).orElseThrow(() -> new RuntimeException("Provider Id not found!"));

        return bookingRepository.findByProvider(provider);
    }

    public Booking cancelBooking(UUID bookingId) {
        Booking booking = bookingRepository.findById(bookingId).orElseThrow(() -> new RuntimeException("Booking Id not found!"));

        booking.setStatus(BookingStatus.CANCELLED);

        return bookingRepository.save(booking);
    }

}
