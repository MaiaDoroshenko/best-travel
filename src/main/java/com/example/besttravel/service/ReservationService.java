package com.example.besttravel.service;

import com.example.besttravel.entity.ReservationEntity;
import com.example.besttravel.model.request.ReservationRequest;
import com.example.besttravel.model.responses.HotelResponse;
import com.example.besttravel.model.responses.ReservationResponse;
import com.example.besttravel.repository.CustomerRepository;
import com.example.besttravel.repository.HotelRepository;
import com.example.besttravel.repository.ReservationRepository;
import com.example.besttravel.service.abstractService.IReservationService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Service
@Transactional
@Slf4j
@AllArgsConstructor
public class ReservationService implements IReservationService {
    private final ReservationRepository reservationRepository;
    private CustomerRepository customerRepository;
    private final HotelRepository hotelRepository;

    @Override
    public ReservationResponse create(ReservationRequest request) {
        var customer = customerRepository.findById(request.getIdClient()).orElseThrow();
        var hotel = hotelRepository.findById(Long.valueOf(request.getIdHotel())).orElseThrow();

        var reservationToPersist =  ReservationEntity.builder()
                .id(UUID.randomUUID())
                .hotel(hotel)
                .customer(customer)
                .totalDays(request.getTotalDays())
                .dateTimeReservation(LocalDateTime.now())
                .dateStart(LocalDate.now().atStartOfDay())
                .dateEnd(LocalDate.now().plusDays(request.getTotalDays()).atStartOfDay())//le sumo los dias que se va a quedar el cliente
                .price(hotel.getPrice().add(hotel.getPrice().multiply(charges_price_porcentage)))
                .build();
        var reservationPersisted = reservationRepository.save(reservationToPersist);
        log.info("Reservation saved whit id: {}", reservationPersisted.getId());


        return entityToResponse(reservationPersisted);
    }

    @Override
    public ReservationResponse read(UUID id) {
        var reservationFromDB = reservationRepository.findById(id).orElseThrow();
        return entityToResponse(reservationFromDB);
    }

    @Override
    public ReservationResponse update(ReservationRequest request, UUID id) {
        var reservationToUpdate = reservationRepository.findById(id).orElseThrow();
        var hotel = hotelRepository.findById(Long.valueOf(request.getIdHotel())).orElseThrow();


        reservationToUpdate.setHotel(hotel);
        reservationToUpdate.setTotalDays(request.getTotalDays());
        reservationToUpdate.setDateTimeReservation(LocalDateTime.now());
        reservationToUpdate.setDateStart(LocalDateTime.now());
        reservationToUpdate.setDateEnd(LocalDateTime.now().plusDays(request.getTotalDays()));
        reservationToUpdate.setPrice(hotel.getPrice().add(hotel.getPrice().multiply(charges_price_porcentage)));

       var reservationUpdated= reservationRepository.save(reservationToUpdate);


        return entityToResponse(reservationUpdated);
    }

    @Override
    public void delete(UUID id) {
        var reservationOnDelete=reservationRepository.findById(id).orElseThrow();
        reservationRepository.delete(reservationOnDelete);

    }
    @Override
    public BigDecimal findPrice(Long hotelId) {
        var hotel=hotelRepository.findById(hotelId).orElseThrow();
        return hotel.getPrice().add(hotel.getPrice().multiply(charges_price_porcentage));
    }

    private ReservationResponse entityToResponse(ReservationEntity entity) {
        var response = new ReservationResponse();
        BeanUtils.copyProperties(entity, response);
        var hotelResponse = new HotelResponse();
        BeanUtils.copyProperties(entity.getHotel(), hotelResponse);
        response.setHotel(hotelResponse);
        return response;
    }

    private static final BigDecimal charges_price_porcentage = BigDecimal.valueOf(0.20);


}
