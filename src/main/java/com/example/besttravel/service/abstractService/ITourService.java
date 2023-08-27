package com.example.besttravel.service.abstractService;

import com.example.besttravel.model.request.TourRequest;
import com.example.besttravel.model.responses.TourResponse;

import java.util.UUID;

public interface ITourService extends SimpleCRUDService<TourRequest, TourResponse, Long> {
    void deleteTicket(UUID ticketId, Long tourId);

    UUID addTicket(Long flyId, Long tourId);

    void deleteReservation(UUID reservationId, Long tourId);

    UUID addReservation(Long reservationId, Long tourId);


}
