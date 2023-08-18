package com.example.besttravel.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity(name="ticket")
public class TicketEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    private LocalDateTime departureDate;//fecha de partida
    private LocalDateTime arrivalDate;//fecha de llegada
    private LocalDateTime purchaseDate;//fecha de compra
    private BigDecimal price;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="fly_id")
    private FlyEntity fly;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name ="tour_id")
    private TourEntity tour;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name ="customer_id")
    private CustomerEntity customer;



}
