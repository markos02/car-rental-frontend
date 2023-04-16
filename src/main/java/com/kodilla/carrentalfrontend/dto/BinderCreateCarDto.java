package com.kodilla.carrentalfrontend.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BinderCreateCarDto {

    private String carGroup;
    private String licensePlate;
    private String fuelType;
    private String transmission;
    private String model;
}
