
package com.AppliBanque.Appli.infrastructure.driving.rest.dto;

import java.time.LocalDateTime;

// Ce record représente une erreur envoyée
// par notre API au format JSON.
//
// Imagine une fiche qui explique
// pourquoi une opération a échoué.

public record ErrorResponse(

        // 1. Le numéro de l'erreur HTTP
        // Exemple : 400, 404, 409 ou 500
        int status,

        // 2. Le type d'erreur
        // Exemple : "Bad Request" ou "Conflict"
        String error,

        // 3. Le message qui explique le problème
        // Exemple : "Ce client existe déjà"
        String message,

        // 4. La date et l'heure de l'erreur
        LocalDateTime timestamp

) {
}
