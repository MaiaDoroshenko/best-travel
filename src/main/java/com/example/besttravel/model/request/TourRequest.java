package com.example.besttravel.model.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;


@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class TourRequest {
    public String customerId;
    private List<TourFlyRequest> flieghts;
    private List<ToutHotelRequest> hotels;
}
