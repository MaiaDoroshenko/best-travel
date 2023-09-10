package com.example.besttravel.model.responses;

import lombok.*;
import lombok.experimental.SuperBuilder;

@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
 @Data
@SuperBuilder
public class ErrorResponse extends BaseErrorResponse{
 private String message;
}
