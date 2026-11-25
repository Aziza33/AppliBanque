
package com.AppliBanque.Appli.infrastructure.driving.rest.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import io.swagger.v3.oas.annotations.media.Schema;

// Un record permet de transporter des données.
// Java 25 crée automatiquement le constructeur et les méthodes d'accès
// Ici, nous recevons les informations saisies
// par une personne qui veut créer un client.
// les données reçues par l'extérieur

@Schema(description = "Informations nécessaires à la création d'un client bancaire")
public record CreateClientRequest(

        // Le prénom ne doit pas être vide.
        // Il doit contenir entre 2 et 50 caractères.
        @Schema(
                description = "Prénom du client",
                example = "Sophie",
                minLength = 2,
                maxLength = 50
        )
        @NotBlank(message = "Le prénom est obligatoire")
        @Size(min = 2, max = 50,
                message = "Le prénom doit contenir entre 2 et 50 caractères")
        String firstName,

        // Le nom ne doit pas être vide.
        // Il doit contenir entre 2 et 50 caractères.
        @Schema(
                description = "Nom du client",
                example = "Martin",
                minLength = 2,
                maxLength = 50
        )
        @NotBlank(message = "Le nom est obligatoire")
        @Size(min = 2, max = 50,
                message = "Le nom doit contenir entre 2 et 50 caractères")
        String lastName

) {
}
