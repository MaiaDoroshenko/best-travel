package com.example.besttravel.entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Builder
@Table(name="tour")
public class TourEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @OneToMany(mappedBy = "tour",fetch = FetchType.LAZY)
    private List<ReservationEntity> reservations;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @OneToMany(mappedBy = "tour",fetch = FetchType.LAZY)
    private List<TicketEntity> tikets;
    @ManyToOne
    @JoinColumn(name ="customer_id")
    private CustomerEntity customer;





}
