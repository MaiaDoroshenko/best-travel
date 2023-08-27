package com.example.besttravel.service.abstractService;

import com.example.besttravel.util.SortType;
import org.springframework.data.domain.Page;

import java.math.BigDecimal;
import java.util.List;

public interface CatalogService <RS>{
    Page<RS> readAll(Integer page, Integer size, SortType sortType);
    List<RS> readPriceLess(BigDecimal price);

    List<RS>readBetweenPrice(BigDecimal min,BigDecimal max);

    String FIELD_BY_SORT="price";// en que propiedad me voy a basar para ordenar los precios



}
