
package com.AppliBanque.Appli.domain.exception;

// Cette classe représente une erreur métier :
// on essaie de créer un client qui existe déjà.

// extends signifie que notre classe hérite
// du fonctionnement de RuntimeException.
public class ClientAlreadyExistsException
        extends RuntimeException {

    // Constructeur de notre exception.
    // Il reçoit un message expliquant le problème.
    public ClientAlreadyExistsException(String message) {

        // super() transmet le message
        // au constructeur de RuntimeException.
        super(message);
    }
}
