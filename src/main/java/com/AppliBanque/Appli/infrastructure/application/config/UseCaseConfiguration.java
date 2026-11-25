
package com.AppliBanque.Appli.infrastructure.application.config;

// On importe notre interface ClientRepository.
// Elle définit comment enregistrer et rechercher les clients.
import com.AppliBanque.Appli.domain.port.ClientRepository;

// On importe le use case qui crée un client.
import com.AppliBanque.Appli.domain.usecase.CreateClient;

// On importe le use case qui liste les clients.
import com.AppliBanque.Appli.domain.usecase.ListClients;

// On importe les annotations Spring.
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


// On dit à Spring :
// "Cette classe explique comment fabriquer certains objets."
@Configuration
public class UseCaseConfiguration {


    // ==========================================
    // 1. CONFIGURER LA CRÉATION D'UN CLIENT
    // ==========================================

    // @Bean demande à Spring de créer et gérer cet objet.
    @Bean
    public CreateClient createClient(
            ClientRepository clientRepository
    ) {

        // Spring nous fournit un repository.
        // On le donne à CreateClient pour qu'il
        // puisse enregistrer des clients.

        return new CreateClient(clientRepository);
    }


    // ==========================================
    // 2. CONFIGURER LA LISTE DES CLIENTS
    // ==========================================

    @Bean
    public ListClients listClients(
            ClientRepository clientRepository
    ) {

        // On utilise le même repository
        // pour retrouver les clients enregistrés.

        return new ListClients(clientRepository);
    }
}
