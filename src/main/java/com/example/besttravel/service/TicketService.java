package com.example.besttravel.service;

import com.example.besttravel.entity.TicketEntity;
import com.example.besttravel.model.request.TicketRequest;
import com.example.besttravel.model.responses.FlyResponse;
import com.example.besttravel.model.responses.TicketResponse;
import com.example.besttravel.repository.CustomerRepository;
import com.example.besttravel.repository.FlyRepository;
import com.example.besttravel.repository.TicketRepository;
import com.example.besttravel.service.abstractService.ITicketService;
import com.example.besttravel.util.BestTravelUtil;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Service
@Transactional
@Slf4j
@AllArgsConstructor
public class TicketService implements ITicketService {

    private final FlyRepository flyRepository;
    private final CustomerRepository customerRepository;
    private final TicketRepository ticketRepository;

    @Override
    public TicketResponse create(TicketRequest request) {
        var fly = flyRepository.findById(Long.valueOf(request.getIdFly())).orElseThrow();
        var customer = customerRepository.findById(request.getIdClient()).orElseThrow();

        var ticketToPersist = TicketEntity.builder()
                .id(UUID.randomUUID())
                .fly(fly)
                .customer(customer)
                .price(fly.getPrice().add(fly.getPrice().multiply(charges_price_porcentage))) //me va a traer el precio del vuelo agregandole el 25%
                .purchaseDate(LocalDateTime.now())//la fecha actual
                .arrivalDate(BestTravelUtil.getRandomLater())
                .departureDate(BestTravelUtil.getRandomSoon())
                .build();

        var ticketPersited = this.ticketRepository.save(ticketToPersist);
        log.info("Ticket saved whit id: {}", ticketPersited.getId());//el{} seria reemplazado por el segundo par, en este caso id


        return this.entityToResponse(ticketPersited);
    }

    @Override
    public TicketResponse read(UUID id) {
        var ticketFromBD = this.ticketRepository.findById(id).orElseThrow();
        return this.entityToResponse(ticketFromBD);
    }

    @Override
    public TicketResponse update(TicketRequest request, UUID id) {
        var ticketToUpdate = ticketRepository.findById(id).orElseThrow();
        var fly = flyRepository.findById(Long.valueOf(request.getIdFly())).orElseThrow();

        ticketToUpdate.setFly(fly);
        ticketToUpdate.setPrice(fly.getPrice().add(fly.getPrice()).multiply(charges_price_porcentage));
        ticketToUpdate.setArrivalDate(BestTravelUtil.getRandomSoon());
        ticketToUpdate.setDepartureDate(BestTravelUtil.getRandomLater());

        var ticketUpdate = this.ticketRepository.save(ticketToUpdate);

        return entityToResponse(ticketUpdate);
    }

    @Override
    public void delete(UUID id) {
        var ticketToDelete=ticketRepository.findById(id).orElseThrow();
        ticketRepository.delete(ticketToDelete);

    }
    @Override
    public BigDecimal findPrice(Long flyId) {
        var fly=flyRepository.findById(flyId).orElseThrow();
        return fly.getPrice().add(fly.getPrice().multiply(charges_price_porcentage));

    }

    private TicketResponse entityToResponse(TicketEntity entity) {
        var response = new TicketResponse();
        BeanUtils.copyProperties(entity, response); //hace el seteo automaticamente de los datos de entity a los de response
        var flyResponse = new FlyResponse();
        BeanUtils.copyProperties(entity.getFly(), flyResponse);
        response.setFly(flyResponse);
        return response;
    }
    private static final BigDecimal charges_price_porcentage=BigDecimal.valueOf(0.25);


}
