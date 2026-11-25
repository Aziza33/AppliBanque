
package com.AppliBanque.Appli.infrastructure.driving.rest.dto;

import io.swagger.v3.oas.annotations.media.Schema;

// Ce record représente les informations
// du client que notre API va renvoyer.
// Les données envoyées à l'extérieur

@Schema(description = "Informations d'un client bancaire renvoyées par l'API")
public record ClientDTO(

        // Identifiant ULID du client
        @Schema(
                description = "Identifiant unique du client au format ULID",
                example = "01ARZ3NDEKTSV4RRFFQ69G5FAV"
        )
        String id,

        // Prénom du client
        @Schema(
                description = "Prénom du client",
                example = "Sophie"
        )
        String firstName,

        // Nom du client
        @Schema(
                description = "Nom du client",
                example = "Martin"
        )
        String lastName

) {
}
