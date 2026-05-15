package com.slotify.backend.service;

import com.slotify.backend.dto.SlotResponse;
import com.slotify.backend.entity.*;
import com.slotify.backend.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class SlotService {

    private final ServiceRepository serviceRepository;
    private final AvailabilityRepository availabilityRepository;
    private final BlockedDatesRepository blockedDatesRepository;
    private final BookingRepository bookingRepository;

    public List<SlotResponse> getAvailableSlots(UUID serviceId, LocalDate date) {
        ServiceEntity service = serviceRepository.findById(serviceId).orElseThrow(() -> new RuntimeException("Service Id not found!"));

        ProviderProfile provider = service.getProvider();

        //Check if date is blocked
        boolean isBlocked = blockedDatesRepository.findByProviderAndBlockedDate(provider, date).isPresent();

        if(isBlocked) {
            return List.of(); //returns empty list
        }

        //Get availability for that dayofWeek
        String dayOfWeek = date.getDayOfWeek().toString();

        List<Availability> availabilityList = availabilityRepository.findByProvider(provider);

        Availability availability = availabilityList.stream()
                .filter(a -> a.getDayofWeek().equals(dayOfWeek))
                .findFirst()
                .orElse(null);

        if(availability == null) {
            return List.of();
        }

        //gneerate slots
        List<SlotResponse> slots = new ArrayList<>();
        LocalDateTime slotStart = LocalDateTime.of(date, availability.getStartTime());
        LocalDateTime slotEnd = LocalDateTime.of(date, availability.getEndTime());
        int duration = service.getDurationMinutes();

        //Loop through day in duration steps
        while(slotStart.plusMinutes(duration).isBefore(slotEnd) ||
            slotStart.plusMinutes(duration).isEqual(slotEnd)) {
            LocalDateTime currentEnd = slotStart.plusMinutes(duration);

            //check if slot is booked
            boolean hasConflict = bookingRepository.existsOverlappingBooking(provider, slotStart, currentEnd);

            slots.add(new SlotResponse(slotStart, currentEnd, !hasConflict));

            //move to next slot
            slotStart = currentEnd;
        }

        return slots;



    }
}
