package com.kodilla.carrentalfrontend.service;

import com.kodilla.carrentalfrontend.client.CarClient;
import com.kodilla.carrentalfrontend.client.CarGroupClient;
import com.kodilla.carrentalfrontend.dto.CarDto;
import com.kodilla.carrentalfrontend.dto.CreateCarDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CarService {

    private final CarClient carClient;
    private final CarGroupClient carGroupClient;

    public List<CarDto> getCars() {
        List<CarDto> cars = carClient.getAllCars();
        for (CarDto car : cars) {
            String carGroupName = carGroupClient.getCarGroup(car.getCarGroupId()).getName();
            car.setCarGroupName(carGroupName);
        }
        return cars;
    }

    public void saveNewCar(CreateCarDto createCarDto) {
        carClient.createCar(createCarDto);
    }

    public List<CarDto> getAvailableCars(LocalDate dateFrom, LocalDate dateTo) {
        List<CarDto> cars = carClient.getAllAvailable(dateFrom, dateTo);
        for (CarDto car : cars) {
            String carGroupName = carGroupClient.getCarGroup(car.getCarGroupId()).getName();
            car.setCarGroupName(carGroupName);
        }
        return cars;
    }
}
