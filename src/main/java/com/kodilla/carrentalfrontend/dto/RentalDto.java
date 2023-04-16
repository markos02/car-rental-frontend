package com.kodilla.carrentalfrontend.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class RentalDto {

    private Integer rentalId;
    private Integer orderId;
    private List<Integer> damagesIds;
    private String status;
}
