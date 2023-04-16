package com.kodilla.carrentalfrontend.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
public class ReturnCarDto {

    private Integer rentalId;
    private LocalDate returnDate;
    private double fuelLevel;
}
