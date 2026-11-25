package com.AppliBanque.Appli.domain.port;

import com.AppliBanque.Appli.domain.model.Client;
import java.util.*;

public interface ClientRepository {
    Client save(Client client);

    List<Client> findAll();

    boolean existsBy(String firstName, String lastName);
}
