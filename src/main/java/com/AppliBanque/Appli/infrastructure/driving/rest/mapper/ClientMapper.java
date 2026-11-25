
package com.AppliBanque.Appli.infrastructure.driving.rest.mapper;

// On importe notre entité métier Client.
import com.AppliBanque.Appli.domain.model.Client;

// On importe le DTO utilisé pour répondre à l'API.
import com.AppliBanque.Appli.infrastructure.driving.rest.dto.ClientDTO;

// On importe le DTO contenant les données reçues.
import com.AppliBanque.Appli.infrastructure.driving.rest.dto.CreateClientRequest;


// Cette classe sert uniquement à convertir des objets.
// Elle n'a pas besoin de Spring.
public final class ClientMapper {

    // Constructeur privé :
    // on empêche la création inutile d'un ClientMapper.
    private ClientMapper() {
    }

    // =========================================
    // 1. TRANSFORMER UN CLIENT EN CLIENTDTO
    // =========================================

    public static ClientDTO toDTO(Client client) {

        // On récupère les 3 informations du Client.
        // Puis on crée un nouvel objet ClientDTO.

        return new ClientDTO(
                client.id(),
                client.firstName(),
                client.lastName()
        );
    }

    // =========================================
    // 2. TRANSFORMER UNE REQUEST EN CLIENT
    // =========================================

    public static Client toClient(
            CreateClientRequest request,
            String id
    ) {

        // La requête ne contient pas d'identifiant.
        // L'ID doit donc être fourni séparément.
        //
        // On construit un Client avec :
        // - l'ULID
        // - le prénom reçu
        // - le nom reçu

        return new Client(
                id,
                request.firstName(),
                request.lastName()
        );
    }
}
