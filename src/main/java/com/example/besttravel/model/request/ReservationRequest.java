package com.example.besttravel.model.request;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class ReservationRequest {


    private String idClient;
    @Positive
    @NotNull(message = "Id hotel is mandatory")
    private Long idHotel;
    private Integer totalDays;
    private String email;
}
