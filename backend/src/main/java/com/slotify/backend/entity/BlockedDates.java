package com.slotify.backend.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Data
@Table(name = "blocked_dates")
@NoArgsConstructor
@AllArgsConstructor
public class BlockedDates {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(name = "blocked_date")
    private LocalDate blockedDate;

    @Column(name = "reason")
    private String reason;

    @ManyToOne
    @JoinColumn(name = "provider_id")
    private ProviderProfile provider;


}
