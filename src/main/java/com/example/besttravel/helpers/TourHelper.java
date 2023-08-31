package com.example.besttravel.helpers;

import com.example.besttravel.entity.*;
import com.example.besttravel.repository.ReservationRepository;
import com.example.besttravel.repository.TicketRepository;
import com.example.besttravel.service.ReservationService;
import com.example.besttravel.service.TicketService;
import com.example.besttravel.util.BestTravelUtil;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

@Transactional
@Component
@AllArgsConstructor
public class TourHelper {
    public final TicketRepository ticketRepository;
    public final ReservationRepository reservationRepository;

    public Set<TicketEntity> createTicket(Set<FlyEntity> flights, CustomerEntity customer) {
        var response = new HashSet<TicketEntity>(flights.size());
        flights.forEach(fly -> {
            var ticketToPersist = TicketEntity.builder()
                    .id(UUID.randomUUID())
                    .fly(fly)
                    .customer(customer)
                    .price(fly.getPrice().add(fly.getPrice().multiply(TicketService.charges_price_porcentage))) //me va a traer el precio del vuelo agregandole el 25%
                    .purchaseDate(LocalDateTime.now())
                    .arrivalDate(BestTravelUtil.getRandomLater())
                    .departureDate(BestTravelUtil.getRandomSoon())
                    .build();
            response.add(this.ticketRepository.save(ticketToPersist));
        });
        return response;
    }

    public Set<ReservationEntity> createReservation(HashMap<HotelEntity, Integer> hotels, CustomerEntity customer) {
        var response = new HashSet<ReservationEntity>(hotels.size());
        hotels.forEach((hotel, totalDays) -> {
            var reservationToPersist = ReservationEntity.builder()
                    .id(UUID.randomUUID())
                    .hotel(hotel)
                    .customer(customer)
                    .totalDays(totalDays)
                    .dateTimeReservation(LocalDateTime.now())
                    .dateStart(LocalDate.now().atStartOfDay())
                    .dateEnd(LocalDate.now().plusDays(totalDays).atStartOfDay())
                    .price(hotel.getPrice().add(hotel.getPrice().multiply(ReservationService.charges_price_porcentage)))
                    .build();
            response.add(this.reservationRepository.save(reservationToPersist));
        });
        return response;

    }
    public TicketEntity createTicket(FlyEntity fly,CustomerEntity customer){
        var ticketToPersist=TicketEntity.builder()
                .id(UUID.randomUUID())
                .fly(fly)
                .customer(customer)
                .price(fly.getPrice().add(fly.getPrice().multiply(TicketService.charges_price_porcentage))) //me va a traer el precio del vuelo agregandole el 25%
                .purchaseDate(LocalDateTime.now())
                .arrivalDate(BestTravelUtil.getRandomLater())
                .departureDate(BestTravelUtil.getRandomSoon())
                .build();
        return this.ticketRepository.save(ticketToPersist);

    }
    public ReservationEntity createReservation(HotelEntity hotel,CustomerEntity customer,Integer totalDays){

        var reservationToPersist = ReservationEntity.builder()
                .id(UUID.randomUUID())
                .hotel(hotel)
                .customer(customer)
                .totalDays(totalDays)
                .dateTimeReservation(LocalDateTime.now())
                .dateStart(LocalDate.now().atStartOfDay())
                .dateEnd(LocalDate.now().plusDays(totalDays).atStartOfDay())
                .price(hotel.getPrice().add(hotel.getPrice().multiply(ReservationService.charges_price_porcentage)))
                .build();
        return reservationRepository.save(reservationToPersist);

    }

}
