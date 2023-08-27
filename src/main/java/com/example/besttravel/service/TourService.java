package com.example.besttravel.service;

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

import java.util.UUID;

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

        return null;
    }

    @Override
    public TourResponse read(Long aLong) {
        return null;
    }

    @Override
    public void delete(Long aLong) {

    }

    @Override
    public void deleteTicket(UUID ticketId, Long tourId) {

    }

    @Override
    public UUID addTicket(Long flyId, Long tourId) {
        return null;
    }

    @Override
    public void deleteReservation(UUID reservationId, Long tourId) {

    }

    @Override
    public UUID addReservation(Long reservationId, Long tourId) {
        return null;
    }


}
