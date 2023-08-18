package com.example.besttravel.controller;

import com.example.besttravel.model.request.ReservationRequest;
import com.example.besttravel.model.responses.ReservationResponse;
import com.example.besttravel.service.abstractService.IReservationService;
import lombok.AllArgsConstructor;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/reservation")
@AllArgsConstructor
public class ReservationController {
    private final IReservationService reservationService;


    @PostMapping("create")
    public ResponseEntity<ReservationResponse> create(@RequestBody ReservationRequest request) {
        if (request.getIdClient() == null || request.getIdHotel() == null||request.getTotalDays()==null) {
            return ResponseEntity.badRequest().build();
        }
        try {
            ReservationResponse response = reservationService.create(request);
            return ResponseEntity.ok(response);
        } catch (InvalidDataAccessApiUsageException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/read/{id}")
    public ResponseEntity<ReservationResponse>read(@PathVariable UUID id){
        return ResponseEntity.ok(reservationService.read(id));
    }

    @PutMapping("update/{id}")
    public ResponseEntity<ReservationResponse>update(@PathVariable UUID id,@RequestBody ReservationRequest request){
        return ResponseEntity.ok(reservationService.update(request,id));
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<Void>delete(@PathVariable UUID id){
      reservationService.delete(id);
      return  ResponseEntity.noContent().build();
    }

    @GetMapping("/priceHotel")
    public ResponseEntity<Map<String, BigDecimal>>hotelPrice(@RequestParam Long hotelId){
        return ResponseEntity.ok(Collections.singletonMap("HotelPrice",reservationService.findPrice(hotelId)));

    }


}
