package com.example.besttravel.model.responses;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class TourResponse {
    private Long id;
    private List<UUID> tiketIds;
    private List<UUID>resertvationIds;

}
