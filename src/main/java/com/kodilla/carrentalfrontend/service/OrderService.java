package com.kodilla.carrentalfrontend.service;

import com.kodilla.carrentalfrontend.client.OrderClient;;
import com.kodilla.carrentalfrontend.dto.OrderDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderClient orderClient;

    public void makeReservation(OrderDto orderDto) {
        orderClient.makeReservation(orderDto);
    }
}
