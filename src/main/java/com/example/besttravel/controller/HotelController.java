package com.example.besttravel.controller;

import com.example.besttravel.model.responses.HotelResponse;
import com.example.besttravel.service.abstractService.IHotelService;
import com.example.besttravel.util.SortType;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

@RestController
@AllArgsConstructor
@RequestMapping("hotel")
public class HotelController {
    private final IHotelService hotelService;

    @GetMapping
    public ResponseEntity<Page<HotelResponse>> getAll(@RequestParam Integer page,
                                                      @RequestParam Integer size, SortType sortType) {
        if (Objects.isNull(sortType)) sortType = SortType.NONE;
        var response = hotelService.readAll(page, size, sortType);
        return response.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(response);
    }

    @GetMapping("less_price")
    public ResponseEntity<List<HotelResponse>> getLessPrice(@RequestParam BigDecimal price) {
        var response = hotelService.readPriceLess(price);
        return response.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(response);
    }

    @GetMapping("between_price")
    public ResponseEntity<List<HotelResponse>> betweenPrice(@RequestParam BigDecimal min, @RequestParam BigDecimal max) {
        var response = hotelService.readBetweenPrice(min, max);
        return response.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(response);
    }

    @GetMapping("rating")
    public ResponseEntity<List<HotelResponse>> readByRaiting(@RequestParam Integer raiting) {
        if (raiting > 4) raiting = 4;
        if ((raiting < 1)) raiting = 1;
        var response = hotelService.readGreaterThan(raiting);
        return response.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(response);
    }
}
