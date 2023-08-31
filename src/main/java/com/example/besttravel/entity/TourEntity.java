package com.example.besttravel.entity;

import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Builder
@Table(name = "tour")
public class TourEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @OneToMany(mappedBy = "tour", fetch = FetchType.LAZY)
    private Set<ReservationEntity> reservations;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @OneToMany(mappedBy = "tour", fetch = FetchType.LAZY)
    private Set<TicketEntity> tikets;
    @ManyToOne
    @JoinColumn(name = "customer_id")
    private CustomerEntity customer;

    @PrePersist
    @PreRemove
    public void upfateFly() {
        this.tikets.forEach(tiket -> tiket.setTour(this));
        this.reservations.forEach(reservation -> reservation.setTour(this));
    }

    public void deleteTicket(UUID id) {
        this.tikets.forEach(tiket -> {
            if (tiket.getId().equals(id)) {
                tiket.setTour(null);
            }
        });
    }

    public void addTicket(TicketEntity ticket) {
        if (Objects.isNull(this.tikets)) this.tikets = new HashSet<>();
        this.tikets.add(ticket);
        this.tikets.forEach(t -> t.setTour(this));
    }

    public void deleteReservation(UUID id) {
        this.reservations.forEach(reservation -> {
            if (reservation.getId().equals(id)) {
                reservation.setTour(null);
            }
        });

    }
    public void addReservation(ReservationEntity reservation){
        if(Objects.isNull(this.reservations))this.reservations=new HashSet<>();
        this.reservations.add(reservation);
        this.reservations.forEach(r->r.setTour(this));
    }

}