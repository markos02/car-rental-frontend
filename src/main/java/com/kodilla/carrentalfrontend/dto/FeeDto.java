package com.kodilla.carrentalfrontend.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class FeeDto {
    private String description;
    private Double value;
}
