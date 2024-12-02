package com.shadow.service;

import com.shadow.entity.Client;
import com.shadow.repository.ClientRepository;
import io.micronaut.core.annotation.NonNull;
import jakarta.inject.Singleton;

import java.time.LocalDateTime;
import java.util.Optional;

@Singleton
public class ClientService {

    private final ClientRepository clientRepository;

    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public Client createClient(Client client) {
        client.setCreatedAt(LocalDateTime.now());
        return clientRepository.save(client);
    }

    public Optional<Client> getClientById(Long id) {
        return clientRepository.findById(id);
    }

    public Optional<Client> findByClientName(String name) {
        return Optional.ofNullable(clientRepository.findByclientName(name));
    }

    public Iterable<Client> getAllClients() {
        return clientRepository.findAll();
    }
}
