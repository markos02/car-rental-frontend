package com.kodilla.carrentalfrontend.client;

import com.kodilla.carrentalfrontend.config.BackendConfig;
import com.kodilla.carrentalfrontend.dto.ClientDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class ClientClient {

    private final RestTemplate restTemplate;
    private final BackendConfig backendConfig;

    public List<ClientDto> getAllClients() {
        String url = backendConfig.getEndpoint() + backendConfig.getClientApi();
        ClientDto[] response = restTemplate.getForObject(url, ClientDto[].class);
        return Optional.ofNullable(response)
                .map(Arrays::asList)
                .orElse(Collections.emptyList());
    }

    public void saveNewClient(ClientDto clientDto) {
        String url = backendConfig.getEndpoint() + backendConfig.getClientApi();
        restTemplate.postForObject(url, clientDto, ClientDto.class);
    }
}
