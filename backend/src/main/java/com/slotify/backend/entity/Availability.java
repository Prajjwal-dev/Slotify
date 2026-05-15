package com.slotify.backend.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;
import java.util.Collection;
import java.util.UUID;

@Entity
@Data
@Table(name = "availability")
@NoArgsConstructor
@AllArgsConstructor
public class Availability {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(name = "day_of_week")
    private String dayofWeek;

    @Column(name = "start_Time")
    private LocalTime startTime;

    @Column(name = "end_Time")
    private LocalTime endTime;

    @ManyToOne
    @JoinColumn(name = "provider_id")
    private ProviderProfile provider;
}
