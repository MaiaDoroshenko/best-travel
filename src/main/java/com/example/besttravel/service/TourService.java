package com.example.besttravel.service;

import com.example.besttravel.entity.*;
import com.example.besttravel.helpers.TourHelper;
import com.example.besttravel.model.request.TourRequest;
import com.example.besttravel.model.responses.FlyResponse;
import com.example.besttravel.model.responses.TourResponse;
import com.example.besttravel.repository.CustomerRepository;
import com.example.besttravel.repository.FlyRepository;
import com.example.besttravel.repository.HotelRepository;
import com.example.besttravel.repository.TourRepsotory;
import com.example.besttravel.service.abstractService.ITourService;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.HashSet;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Transactional
@AllArgsConstructor
public class TourService implements ITourService {
    public final TourRepsotory tourRepsotory;
    public final FlyRepository flyRepository;
    public final HotelRepository hotelRepository;
    public final CustomerRepository customerRepository;
    public final TourHelper tourHelper;


    @Override
    public TourResponse create(TourRequest request) {
        var customer = customerRepository.findById(request.getCustomerId()).orElseThrow();
        var flights = new HashSet<FlyEntity>();
        request.getFlieghts().forEach(fly -> flights.add(this.flyRepository.findById(fly.getId()).orElseThrow()));
        var hotels = new HashMap<HotelEntity, Integer>();
        request.getHotels().forEach(hotel -> hotels.put(this.hotelRepository.findById(hotel.getId()).orElseThrow(), hotel.getTotalDays()));
        var tourToSave = TourEntity.builder()
                .tikets(this.tourHelper.createTicket(flights, customer))
                .reservations(this.tourHelper.createReservation(hotels, customer))
                .customer(customer)
                .build();
        var tourSaved = this.tourRepsotory.save(tourToSave);

        return TourResponse.builder()
                .resertvationIds(tourSaved.getReservations().stream().map(ReservationEntity::getId).collect(Collectors.toSet()))
                .tiketIds(tourSaved.getTikets().stream().map(TicketEntity::getId).collect(Collectors.toSet()))
                .id(tourSaved.getId())
                .build();
    }

    @Override
    public TourResponse read(Long id) {
        var tourFromDb = tourRepsotory.findById(id).orElseThrow();
        return TourResponse.builder()
                .resertvationIds(tourFromDb.getReservations().stream().map(ReservationEntity::getId).collect(Collectors.toSet()))
                .tiketIds(tourFromDb.getTikets().stream().map(TicketEntity::getId).collect(Collectors.toSet()))
                .id(tourFromDb.getId())
                .build();
    }

    @Override
    public void delete(Long id) {
        var toutToDelete = tourRepsotory.findById(id).orElseThrow();
        tourRepsotory.delete(toutToDelete);
    }

    @Override
    public void deleteTicket( Long tourId,UUID ticketId) {

        var tourtUpdate = tourRepsotory.findById(tourId).orElseThrow();
        tourtUpdate.deleteTicket(ticketId);
        this.tourRepsotory.save(tourtUpdate);


    }

    @Override
    public UUID addTicket(Long flyId, Long tourId) {
        var tourUpdate = tourRepsotory.findById(tourId).orElseThrow();
        var fly = flyRepository.findById(flyId).orElseThrow();
        var ticket = tourHelper.createTicket(fly, tourUpdate.getCustomer());
        tourUpdate.addTicket(ticket);
        tourRepsotory.save(tourUpdate);
        return ticket.getId();
    }

    @Override
    public void deleteReservation(Long tourid,UUID reservationId ) {
        var tourtUpdate = tourRepsotory.findById(tourid).orElseThrow();
        tourtUpdate.deleteReservation(reservationId);
        this.tourRepsotory.save(tourtUpdate);
    }

    @Override
    public UUID addReservation(Long tourId, Long hotelId,Integer totalDays) {
        var tourUpdate = tourRepsotory.findById(tourId).orElseThrow();
        var hotel =hotelRepository.findById(hotelId).orElseThrow();
        var reservation = tourHelper.createReservation(hotel, tourUpdate.getCustomer(),totalDays);
        tourUpdate.addReservation(reservation);
        tourRepsotory.save(tourUpdate);
        return reservation.getId();
    }


}
