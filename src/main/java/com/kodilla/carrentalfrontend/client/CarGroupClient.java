package com.kodilla.carrentalfrontend.client;

import com.kodilla.carrentalfrontend.config.BackendConfig;
import com.kodilla.carrentalfrontend.dto.CarGroupDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.security.PublicKey;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class CarGroupClient {

    private final RestTemplate restTemplate;
    private final BackendConfig backendConfig;

    public List<CarGroupDto> getAllCarGroups() {
        String url = backendConfig.getEndpoint() + backendConfig.getCarGroupsApi();
        CarGroupDto[] response = restTemplate.getForObject(url, CarGroupDto[].class);
        return Optional.ofNullable(response)
                .map(Arrays::asList)
                .orElse(Collections.emptyList());
    }

    public CarGroupDto getCarGroup(Integer carGroupId) {
        String url = backendConfig.getEndpoint() + backendConfig.getCarGroupsApi() + "/" + carGroupId;
        return restTemplate.getForObject(url, CarGroupDto.class);
    }

    public void addNewGroup(CarGroupDto carGroupDto) {
        String url = backendConfig.getEndpoint() + backendConfig.getCarGroupsApi();
        restTemplate.postForObject(url, carGroupDto, CarGroupDto.class);
    }
}
