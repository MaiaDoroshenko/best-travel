package com.example.besttravel.model.request;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class ReservationRequest {


    private String idClient;
    @Positive
    @NotNull(message = "Id hotel is mandatory")
    private Long idHotel;
    @Min(value =1,message = "Min one day to make reservation")
    @Max(value = 15, message = " Max 15 days to make reservation")
    @NotNull(message = "total days is mandatory")
    private Integer totalDays;
    @Email(message = "Invalid Email")
    private String email;
}
