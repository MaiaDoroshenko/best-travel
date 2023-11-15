package com.example.besttravel.model.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class TicketRequest {//TicketRequest: Se utiliza para recibir información entrante desde el cliente o la capa de presentación para crear o modificar tickets de vuelo.
    @NotNull(message = "Id is client mandatory")
    private String idClient;
    @NotNull(message = "Id fly is mandatory")
    private String idFly;

}
