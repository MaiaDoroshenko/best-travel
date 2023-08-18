package com.example.besttravel.service.abstractService;

import com.example.besttravel.model.request.TicketRequest;
import com.example.besttravel.model.responses.TicketResponse;
import com.example.besttravel.service.abstractService.CRUDService;

import java.math.BigDecimal;
import java.util.UUID;

public interface ITicketService extends CRUDService<TicketRequest,TicketResponse, UUID> {
    BigDecimal findPrice(Long flyId);//busco el precio del vuelo

}
