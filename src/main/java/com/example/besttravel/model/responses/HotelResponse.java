package com.example.besttravel.model.responses;

import com.example.besttravel.entity.HotelEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class HotelResponse {
    private String address;
    private Integer rating;
    private BigDecimal price;
    private String name;
    private Long id;

}
