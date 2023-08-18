package com.example.besttravel.controller;

import com.example.besttravel.model.request.TicketRequest;
import com.example.besttravel.model.responses.TicketResponse;
import com.example.besttravel.service.abstractService.ITicketService;
import lombok.AllArgsConstructor;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/ticket")
@AllArgsConstructor
public class TicketController {
    private final ITicketService ticketService;//se inyecta mediante la interfaz

    @PostMapping("create")
    public ResponseEntity<TicketResponse> create(@RequestBody TicketRequest request) {
        if (request.getIdClient() == null || request.getIdFly() == null) {
            return ResponseEntity.badRequest().build();
        }
        try {
            TicketResponse response = ticketService.create(request);
            return ResponseEntity.ok(response);
        } catch (InvalidDataAccessApiUsageException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();

        }
    }

    @GetMapping("/read/{id}")
    public ResponseEntity<TicketResponse> read(@PathVariable UUID id) {
        return ResponseEntity.ok(ticketService.read(id));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<TicketResponse> update(@PathVariable UUID id, @RequestBody TicketRequest request) {
        return ResponseEntity.ok(this.ticketService.update(request, id));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        ticketService.delete(id);
        return ResponseEntity.noContent().build();// cuando quiero indicar que el objeto se elimino sin proporcionar detalles adicionales
    }
    @GetMapping("/priceFly")
    public ResponseEntity<Map<String, BigDecimal>> flyPrice(@RequestParam Long flyId) {
        return ResponseEntity.ok(Collections.singletonMap("flyPrice",this.ticketService.findPrice(flyId)));
    }

}
