package com.example.besttravel.controller;

import com.example.besttravel.model.request.TourRequest;
import com.example.besttravel.model.responses.TourResponse;
import com.example.besttravel.service.abstractService.ITourService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("tour")
@AllArgsConstructor
public class TourController {
    private final ITourService tourService;

    @PostMapping("create")
    public ResponseEntity<TourResponse> create(@RequestBody TourRequest request) {
        return ResponseEntity.ok(tourService.create(request));
    }

    @GetMapping("/read/{id}")
    public ResponseEntity<TourResponse> read(@PathVariable Long id) {
        return ResponseEntity.ok(tourService.read(id));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        tourService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}/remove_ticket/{ticketId}")
    public ResponseEntity<Void> deleteTicket(@PathVariable Long id, @PathVariable UUID ticketId) {
        tourService.deleteTicket(id, ticketId);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}/add_ticket/{flyId}")
    public ResponseEntity<Map<String, UUID>> addTicket(@PathVariable Long id, @PathVariable Long flyId) {
        var response = Collections.singletonMap("ticketId", this.tourService.addTicket(id, flyId));
        return ResponseEntity.ok(response);
    }

    @PatchMapping("/{id}/remove_reservation/{reservationId}")
    public ResponseEntity<Void> deleteReservatios(@PathVariable Long id, @PathVariable UUID reservationId) {
        tourService.deleteReservation(id, reservationId);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}/add_reservation/{hotelId}")
    public ResponseEntity<Map<String, UUID>> addReservation(@PathVariable Long tourId,
                                                            @PathVariable Long hotelId,
                                                            @RequestParam Integer totalDays) {
        var response = Collections.singletonMap("ticketId", this.tourService.addReservation(tourId, hotelId,totalDays));
        return ResponseEntity.ok(response);
    }

}
