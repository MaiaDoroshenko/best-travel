package com.example.besttravel.controller;

import com.example.besttravel.model.responses.FlyResponse;
import com.example.besttravel.service.abstractService.IFlyService;
import com.example.besttravel.util.SortType;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

@RestController
@AllArgsConstructor
@RequestMapping("fly")
public class FlyController {
    private final IFlyService flyService;

    @GetMapping
    public ResponseEntity<Page<FlyResponse>> getAll(@RequestParam Integer page,
                                                    @RequestParam Integer size,
                                                    @RequestHeader(required = false) SortType sortType) {

        if (Objects.isNull(sortType))
            sortType = SortType.NONE;//si el objeto sortType viene nulo le asigo None para que venga por defecto
        var response = flyService.readAll(page, size, sortType);
        return response.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(response);
    }

    @GetMapping("less_price")
    public ResponseEntity<List<FlyResponse>>getLessPrice(@RequestParam BigDecimal price){
        var response=flyService.readPriceLess(price);
        return response.isEmpty()?ResponseEntity.noContent().build():ResponseEntity.ok(response);
    }

    @GetMapping("between_price")
    public ResponseEntity<List<FlyResponse>>betweenPrice(@RequestParam BigDecimal min,@RequestParam BigDecimal max){
        var response=flyService.readBetweenPrice(min,max);
        return response.isEmpty()?ResponseEntity.noContent().build():ResponseEntity.ok(response);
    }
}