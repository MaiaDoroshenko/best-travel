package com.example.besttravel.service.abstractService;

import com.example.besttravel.model.request.TourRequest;
import com.example.besttravel.model.responses.TourResponse;

import java.util.UUID;

public interface ITourService extends SimpleCRUDService<TourRequest, TourResponse, Long> {
    void deleteTicket( Long tourId,UUID ticketId);

    UUID addTicket(Long flyId, Long tourId);

    void deleteReservation( Long tourId,UUID reservationId);

    UUID addReservation(Long flyId, Long tourId,Integer totalDays);


}
