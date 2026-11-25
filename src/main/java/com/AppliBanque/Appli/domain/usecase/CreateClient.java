package com.AppliBanque.Appli.domain.usecase;

import com.AppliBanque.Appli.domain.port.ClientRepository;
import com.AppliBanque.Appli.domain.model.Client;
import com.github.f4b6a3.ulid.UlidCreator;
import com.AppliBanque.Appli.domain.exception.ClientAlreadyExistsException;


public class CreateClient {
    private final ClientRepository clientRepository;
       public CreateClient(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;

    }


    public Client execute(String firstName, String lastName) {


        //  Validation des champs
        if (firstName == null || firstName.isBlank()) {
            throw new IllegalArgumentException(
                    "Le prénom est obligatoire"
            );
        }

        if (lastName == null || lastName.isBlank()) {
            throw new IllegalArgumentException(
                    "Le nom est obligatoire"
            );
        }

        if (clientRepository.existsBy(firstName, lastName)) {
            throw new ClientAlreadyExistsException
                    (
                            "Ce client existe déjà"
                    );
        }
     //   Génération d'un identifiant ULID
        String id = UlidCreator.getUlid().toString();

      //  Création de l'entité Client
        Client client = new Client(
                id,
                firstName,
                lastName
        );

        // Enregistrement du client
        return clientRepository.save(client);
    }
}
