package com.kodilla.carrentalfrontend.client;

import com.kodilla.carrentalfrontend.config.BackendConfig;
import com.kodilla.carrentalfrontend.dto.CarDto;
import com.kodilla.carrentalfrontend.dto.CreateCarDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class CarClient {

    private final RestTemplate restTemplate;
    private final BackendConfig backendConfig;

    public List<CarDto> getAllCars() {
        String url = backendConfig.getEndpoint() + backendConfig.getCarsApi();
        CarDto[] response = restTemplate.getForObject(url, CarDto[].class);
        return Optional.ofNullable(response)
                .map(Arrays::asList)
                .orElse(Collections.emptyList());
    }

    public CarDto getCar(Integer carId) {
        String url = backendConfig.getEndpoint() + backendConfig.getCarsApi() + "/" + carId;
        return restTemplate.getForObject(url, CarDto.class);
    }

    public CarDto createCar(CreateCarDto createCarDto) {
        String url = backendConfig.getEndpoint() + backendConfig.getCarsApi();
        return restTemplate.postForObject(url, createCarDto, CarDto.class);
    }

    public CarDto updateCar(CarDto carDto) {
        String url = backendConfig.getEndpoint() + backendConfig.getCarsApi();
        return restTemplate.patchForObject(url, carDto, CarDto.class);
    }

    public String checkIfAvailable(Integer carId, LocalDate dateFrom, LocalDate dateTo) {
        String url = backendConfig.getEndpoint() + backendConfig.getCarsApi() + "/isAvailable/" + carId + "/" + dateFrom + "/" + dateTo;
        ResponseEntity response = restTemplate.getForObject(url, ResponseEntity.class);
        return response.getBody().toString();
    }

    public List<CarDto> getAllAvailable(LocalDate dateFrom, LocalDate dateTo) {
        String url = backendConfig.getEndpoint() + backendConfig.getCarsApi() + "/allAvailable/" + dateFrom + "/" + dateTo;
        CarDto[] response = restTemplate.getForObject(url, CarDto[].class);
        return Optional.ofNullable(response)
                .map(Arrays::asList)
                .orElse(Collections.emptyList());
    }
}
