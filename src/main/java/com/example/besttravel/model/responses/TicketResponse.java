package com.example.besttravel.model.responses;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class TicketResponse {// Se utiliza para enviar información como respuesta hacia el cliente o la capa de presentación, mostrando los detalles de los tickets de vuelo de una manera más amigable y comprensible.

    private UUID id;
    @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "yyyy-MM-dd HH:mm")//NOS PERMITE UTILIZAR DIFERENTES ANOTACIONES PARA FORMATOS DE FECHAS
    private LocalDateTime departureDate;
    @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime arrivalDate;
    @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "yyyy-MM-dd HH:mm")
    private LocalDate purchaseDate;
    private BigDecimal price;
    private FlyResponse fly;

}
