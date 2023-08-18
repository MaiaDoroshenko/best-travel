package com.example.besttravel.service.abstractService;

import com.example.besttravel.model.request.ReservationRequest;
import com.example.besttravel.model.responses.ReservationResponse;
import com.example.besttravel.service.abstractService.CRUDService;

import java.math.BigDecimal;
import java.util.UUID;

public interface IReservationService extends CRUDService<ReservationRequest, ReservationResponse, UUID> {
    BigDecimal findPrice(Long hotelId);

}
