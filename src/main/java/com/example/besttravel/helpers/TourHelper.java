package com.example.besttravel.helpers;

import com.example.besttravel.repository.ReservationRepository;
import com.example.besttravel.repository.TicketRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Component
@AllArgsConstructor
public class TourHelper {
    public final TicketRepository ticketRepository;
    public final ReservationRepository reservationRepository;
}
