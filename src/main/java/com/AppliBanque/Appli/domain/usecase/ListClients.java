

package com.AppliBanque.Appli.domain.usecase;

import com.AppliBanque.Appli.domain.model.Client;
import com.AppliBanque.Appli.domain.port.ClientRepository;

import java.util.List;
import java.util.Objects;

public class ListClients {

    private final ClientRepository clientRepository;

    // Injection du repository par constructeur
    public ListClients(ClientRepository clientRepository) {
        this.clientRepository = Objects.requireNonNull(
                clientRepository,
                "ClientRepository obligatoire"
        );
    }

    // Récupérer tous les clients
    public List<Client> execute() {
        return clientRepository.findAll();
    }
}
