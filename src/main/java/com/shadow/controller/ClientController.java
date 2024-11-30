package com.shadow.controller;

import com.shadow.entity.Client;
import com.shadow.service.ClientService;
import io.micronaut.http.annotation.*;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@Controller("/client")
@RequiredArgsConstructor
public class ClientController {

    private final ClientService clientService;

    @Post
    public Client createClient(@Body Client client) {
        return clientService.createClient(client);
    }

    @Get("/{id}")
    public Client findClientById(@PathVariable Long id) {
        return clientService.getClientById(id).orElse(null);
    }

    @Get
    public Iterable<Client> getAllClients() {
        return clientService.getAllClients();
    }
}
