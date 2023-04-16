package com.kodilla.carrentalfrontend.client;

import com.kodilla.carrentalfrontend.config.BackendConfig;
import com.kodilla.carrentalfrontend.dto.OrderDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
@RequiredArgsConstructor
public class OrderClient {

    private final RestTemplate restTemplate;
    private final BackendConfig backendConfig;

    public OrderDto makeReservation (OrderDto orderDto) {
        String url = backendConfig.getEndpoint() + backendConfig.getOrdersApi();
        return restTemplate.postForObject(url, orderDto, OrderDto.class);
    }
}
