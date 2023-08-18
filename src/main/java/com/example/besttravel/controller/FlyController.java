package com.example.besttravel.controller;

import com.example.besttravel.model.responses.FlyResponse;
import com.example.besttravel.service.abstractService.IFlyService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("fly")
public class FlyController {
    private final IFlyService flyService;

    public ResponseEntity<Page<FlyResponse>>getAll(@RequestParam Integer Page,
                                                   @RequestParam Integer size,
                                                   @Re)

}
