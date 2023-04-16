package com.kodilla.carrentalfrontend.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;


@Component
@Getter
public class BackendConfig {

    @Value("${app.endpoint}")
    private String endpoint;

    @Value("${cars.api.endpoint}")
    private String carsApi;

    @Value("${cargroups.api.endpoint}")
    private String carGroupsApi;

    @Value("${clients.api.endpoint}")
    private String clientApi;

    @Value("${damages.api.endpoint}")
    private String damagesApi;

    @Value("${orders.api.endpoint}")
    private String ordersApi;

    @Value("${rentals.api.endpoint}")
    private String rentalsApi;

    public String getEndpoint() {
        return endpoint;
    }

    public String getCarsApi() {
        return carsApi;
    }
}
