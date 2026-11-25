package com.AppliBanque.Appli.domain.model;

public record Client (
    String id,
    String firstName,
    String lastName
)
{
    // Validation des champs

    public Client {
        if (id == null || id.isBlank()) {

            throw new IllegalArgumentException(
                    "ID obligatoire"
            );
        }

        if (lastName == null || lastName.isBlank ()) {
            throw new IllegalArgumentException(
                    "Nom obligatoire"
            );
        }

        if (firstName == null || firstName.isBlank() ) {
            throw new IllegalArgumentException(
                    "Prénom obligatoire"
            );
        }

}

}

