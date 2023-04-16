package com.kodilla.carrentalfrontend.service;

import com.kodilla.carrentalfrontend.client.ClientClient;
import com.kodilla.carrentalfrontend.dto.ClientDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ClientService {

    private final ClientClient clientClient;

    public List<ClientDto> getAllClients() {
        return clientClient.getAllClients();
    }

    public void saveNewClient(ClientDto clientDto) {
        clientClient.saveNewClient(clientDto);
    }
}
