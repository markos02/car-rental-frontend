package com.kodilla.carrentalfrontend.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CarDto {

    private Integer carId;
    private Integer carGroupId;
    private String carGroupName;
    private List<Integer> damagesIds;
    private List<Integer> ordersIds;
    private String licensePlate;
    private String fuelType;
    private String transmission;
    private String model;
}
