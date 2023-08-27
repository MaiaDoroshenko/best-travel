package com.example.besttravel.repository;

import com.example.besttravel.entity.HotelEntity;
import com.example.besttravel.entity.ReservationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository

public interface HotelRepository extends JpaRepository<HotelEntity,Long> {
    List<HotelEntity>findByPriceLessThan(BigDecimal price);
    List<HotelEntity>findByPriceIsBetween(BigDecimal min,BigDecimal max);
    List<HotelEntity>findByRatingGreaterThan(Integer raiting);


}