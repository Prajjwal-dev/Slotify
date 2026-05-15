package com.slotify.backend.repository;

import com.slotify.backend.entity.Booking;
import com.slotify.backend.entity.Profile;
import com.slotify.backend.entity.ProviderProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.awt.print.Book;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public interface BookingRepository extends JpaRepository<Booking, UUID> {

    List<Booking> findByCustomer(Profile customer);

    List<Booking> findByProvider(ProviderProfile provider);

    @Query("SELECT COUNT(b) > 0 FROM Booking b WHERE b.provider = :provider " +
    "AND b.startTime < :endTime " +
    "AND b.endTime > :startTime " +
    "AND b.status != 'CANCELLED'")

    boolean existsOverlappingBooking(
            @Param("provider") ProviderProfile provider,
            @Param("startTime") LocalDateTime startTime,
            @Param("endTime") LocalDateTime endTime
    );

}