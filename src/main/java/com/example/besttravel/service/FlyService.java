package com.example.besttravel.service;

import com.example.besttravel.entity.FlyEntity;
import com.example.besttravel.model.responses.FlyResponse;
import com.example.besttravel.repository.FlyRepository;
import com.example.besttravel.service.abstractService.IFlyService;
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
@Transactional
public class FlyService implements IFlyService {
    private final FlyRepository flyRepository;

    @Override
    public Page<FlyResponse> readAll(Integer page, Integer size, SortType sortType) {
        PageRequest pageRequest = null;
        switch (sortType) {
            case NONE:
                pageRequest = PageRequest.of(page, size); //Si sortType es NONE, configura pageRequest utilizando la paginación básica (sin ordenamiento explícito).
                break;
            case LOWER:
                pageRequest = PageRequest.of(page, size, Sort.by(FIELD_BY_SORT).ascending());//Si sortType es LOWER, configura pageRequest utilizando paginación y ordenamiento ascendente (menor a mayor)
                break;
            case UPPER:
                pageRequest = PageRequest.of(page, size, Sort.by(FIELD_BY_SORT).descending());//Si sortType es UPPER, configura pageRequest utilizando paginación y ordenamiento descendente (mayor a menor)
                break;
        }
        return flyRepository.findAll(pageRequest).map(this::entityToResponse);
    }

    @Override
    public List<FlyResponse> readPriceLess(BigDecimal price) {

        return flyRepository.selectLessPrice(price)
                .stream()
                .map(this::entityToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<FlyResponse> readBetweenPrice(BigDecimal min, BigDecimal max) {
        return flyRepository.selectBetweenPrice(min, max)
                .stream().map(this::entityToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<FlyResponse> readByOriginDestiny(String origin, String destiny) {
        return flyRepository.selectOriginDestiny(origin, destiny)
                .stream().map(this::entityToResponse)
                .collect(Collectors.toList());
    }

    private FlyResponse entityToResponse(FlyEntity flyEntity) {
        FlyResponse response = new FlyResponse();
        BeanUtils.copyProperties(flyEntity, response);
        return response;
    }
}
