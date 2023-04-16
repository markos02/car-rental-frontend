package com.kodilla.carrentalfrontend.service;

import com.kodilla.carrentalfrontend.client.CarGroupClient;
import com.kodilla.carrentalfrontend.dto.CarGroupDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CarGroupService {

    private final CarGroupClient carGroupClient;

    public List<CarGroupDto> getAllCarGroups() {
        return carGroupClient.getAllCarGroups();
    }

    public void addNewGroup(CarGroupDto carGroupDto) {
        carGroupClient.addNewGroup(carGroupDto);
    }
}
