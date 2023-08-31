package com.example.besttravel.service;

import com.example.besttravel.entity.HotelEntity;
import com.example.besttravel.model.responses.HotelResponse;
import com.example.besttravel.repository.HotelRepository;
import com.example.besttravel.service.abstractService.IHotelService;
import com.example.besttravel.util.SortType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Builder
@Transactional(readOnly = true)
public class HotelService implements IHotelService {
    private final HotelRepository hotelRepository;

    @Override
    public Page<HotelResponse> readAll(Integer page, Integer size, SortType sortType) {
        PageRequest pageRequest = null;
        switch (sortType) {
            case NONE:
                pageRequest = PageRequest.of(page, size);
                break;
            case LOWER:
                pageRequest = PageRequest.of(page, size, Sort.by(FIELD_BY_SORT).ascending());
                break;
            case UPPER:
                pageRequest = PageRequest.of(page, size, Sort.by(FIELD_BY_SORT).descending());
                break;
        }
        return hotelRepository.findAll(pageRequest).map(this::entityToResponse);
    }

    @Override
    public List<HotelResponse> readPriceLess(BigDecimal price) {
        return hotelRepository.findByPriceLessThan(price)
                .stream()
                .map(this::entityToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<HotelResponse> readBetweenPrice(BigDecimal min, BigDecimal max) {
        return hotelRepository.findByPriceIsBetween(min, max)
                .stream()
                .map(this::entityToResponse)
                .collect(Collectors.toList());
    }
    @Override
    public List<HotelResponse> readGreaterThan(Integer raiting) {
        return hotelRepository.findByRatingGreaterThan(raiting)
                .stream()
                .map(this::entityToResponse)
                .collect(Collectors.toList());
    }

    private HotelResponse entityToResponse(HotelEntity entity) {
        HotelResponse response = new HotelResponse();
        BeanUtils.copyProperties(entity, response);
        return response;
    }


}
