package com.example.besttravel.service.abstractService;

import com.example.besttravel.model.responses.FlyResponse;
import com.example.besttravel.util.SortType;
import org.springframework.data.domain.Page;

import java.math.BigDecimal;
import java.util.List;

public interface IFlyService extends CatalogService<FlyResponse> {
    List<FlyResponse> readByOriginDestiny(String origin, String destiny);


}
