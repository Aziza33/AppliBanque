
package com.AppliBanque.Appli.infrastructure.driving.rest;

import jakarta.validation.Valid;

// Les deux use cases
import com.AppliBanque.Appli.domain.usecase.CreateClient;
import com.AppliBanque.Appli.domain.usecase.ListClients;

// L'entité Client
import com.AppliBanque.Appli.domain.model.Client;

// Les DTO
import com.AppliBanque.Appli.infrastructure.driving.rest.dto.ClientDTO;
import com.AppliBanque.Appli.infrastructure.driving.rest.dto.CreateClientRequest;

// Les outils Spring pour créer une API REST
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;



import java.util.List;


@Tag(
        name = "Gestion des clients",
        description = "API permettant de créer et consulter les clients bancaires"
)

// Cette annotation indique à Spring :
// "Cette classe reçoit les requêtes HTTP."
@RestController

// Toutes les routes commencent par /api/clients
@RequestMapping("/api/clients")
public class ClientController {

    // Nos deux outils pour travailler avec les clients
    private final CreateClient createClient;
    private final ListClients listClients;

    // Spring nous fournit les deux use cases.
    public ClientController(
            CreateClient createClient,
            ListClients listClients
    ) {
        this.createClient = createClient;
        this.listClients = listClients;
    }

    // ========================================
    // 1. RÉCUPÉRER TOUS LES CLIENTS
    // ========================================


    @Operation(
            summary = "Lister les clients",
            description = "Retourne la liste de tous les clients enregistrés"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Liste des clients récupérée avec succès"
    )

    // Cette méthode répond à GET /api/clients
    @GetMapping
    public List<ClientDTO> getAllClients() {

        // On demande au use case tous les clients.
        return listClients.execute()
                .stream()

                // Pour chaque Client,
                // on crée un ClientDTO.
                .map(client -> new ClientDTO(
                        client.id(),
                        client.firstName(),
                        client.lastName()
                ))

                // On rassemble les DTO dans une liste.
                .toList();
    }

    // ========================================
    // 2. CRÉER UN NOUVEAU CLIENT
    // ========================================


    @Operation(
            summary = "Créer un client",
            description = "Enregistre un nouveau client bancaire avec un identifiant ULID"
    )
    @ApiResponse(responseCode = "201", description = "Client créé avec succès")
    @ApiResponse(responseCode = "400", description = "Données du client invalides")
    @ApiResponse(responseCode = "409", description = "Client déjà existant")

    // Cette méthode répond à POST /api/clients
    @PostMapping
    public ResponseEntity<ClientDTO> create(
        @Valid  @RequestBody CreateClientRequest request
    ) {

        // On récupère le prénom et le nom
        // puis on demande au use case de créer le client.
        Client client = createClient.execute(
                request.firstName(),
                request.lastName()
        );

        // On transforme notre Client en ClientDTO.
        ClientDTO response = new ClientDTO(
                client.id(),
                client.firstName(),
                client.lastName()
        );

        // On renvoie le client créé
        // avec le statut HTTP 201 Created.
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }
}

// Annotation	Explication très simple
//@RestController	Cette classe reçoit les appels de l'API
//@RequestMapping	Définit l'adresse commune
//@GetMapping	Répond à une demande de lecture
//@PostMapping	Répond à une demande de création
//@RequestBody	Transforme le JSON reçu en objet Java
