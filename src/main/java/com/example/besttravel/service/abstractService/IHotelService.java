package com.example.besttravel.service.abstractService;

import com.example.besttravel.model.responses.HotelResponse;

import java.util.List;

public interface IHotelService extends CatalogService<HotelResponse>{

    List<HotelResponse>readGreaterThan(Integer raiting);
}
