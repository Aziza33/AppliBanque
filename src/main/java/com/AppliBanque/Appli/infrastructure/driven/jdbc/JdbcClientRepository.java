
package com.AppliBanque.Appli.infrastructure.driven.jdbc;

// On importe notre modèle Client.
import com.AppliBanque.Appli.domain.model.Client;

// On importe notre interface ClientRepository.
import com.AppliBanque.Appli.domain.port.ClientRepository;

// Permet d'indiquer à Spring que cette classe
// est responsable de l'accès aux données.
import org.springframework.stereotype.Repository;

// Permet d'exécuter des requêtes SQL
// avec des paramètres nommés.
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

// Permet de transformer une ligne SQL en objet Java.
import org.springframework.jdbc.core.RowMapper;

// Permet de retourner une liste de clients.
import java.util.List;

// Permet d'associer un paramètre SQL à une valeur.
import java.util.Map;


@Repository
public class JdbcClientRepository implements ClientRepository {


    // ========================================
    // 1. INJECTION DE NAMEDPARAMETERJDBCTEMPLATE
    // ========================================

    // Cet objet permet de communiquer avec PostgreSQL.
    private final NamedParameterJdbcTemplate jdbcTemplate;

    // Spring injecte automatiquement cet objet
    // grâce à l'auto-configuration.
    public JdbcClientRepository(
            NamedParameterJdbcTemplate jdbcTemplate) {

        this.jdbcTemplate = jdbcTemplate;
    }


    // ========================================
    // 2. CRÉER LE ROWMAPPER
    // ========================================

    // Le RowMapper transforme chaque ligne
    // de la table clients en objet Client.

    private static final RowMapper<Client> MAPPER =
            (rs, rowNum) -> new Client(

                    rs.getString("id"),
                    rs.getString("first_name"),
                    rs.getString("last_name")

            );


    // ========================================
    // 3. ENREGISTRER UN CLIENT : save()
    // ========================================

    @Override
    public Client save(Client client) {

        // On prépare la requête SQL.
        // Les paramètres nommés commencent par ":".

        String sql = """
                INSERT INTO clients (id, first_name, last_name)
                VALUES (:id, :firstName, :lastName)
                """;

        // On associe chaque paramètre SQL
        // à une valeur de notre objet Client.

        Map<String, Object> params = Map.of(
                "id", client.id(),
                "firstName", client.firstName(),
                "lastName", client.lastName()
        );

        // update() exécute la requête INSERT.
        jdbcTemplate.update(sql, params);

        // On retourne le client enregistré.
        return client;
    }


    // ========================================
    // 4. LISTER LES CLIENTS : findAll()
    // ========================================

    @Override
    public List<Client> findAll() {

        // On récupère les clients dans PostgreSQL.
        String sql = """
                SELECT id, first_name, last_name
                FROM clients
                """;

        // query() exécute la requête SELECT.
        // MAPPER transforme chaque ligne en objet Client.

        return jdbcTemplate.query(sql, MAPPER);
    }


    // ========================================
    // 5. VÉRIFIER SI UN CLIENT EXISTE : existsBy()
    // ========================================

    @Override
    public boolean existsBy(String firstName, String lastName) {

        // EXISTS retourne true si un client existe.
        // LOWER permet d'ignorer les différences
        // entre majuscules et minuscules.

        String sql = """
                SELECT EXISTS (
                    SELECT 1
                    FROM clients
                    WHERE LOWER(first_name) = LOWER(:firstName)
                    AND LOWER(last_name) = LOWER(:lastName)
                )
                """;

        // On associe les paramètres aux valeurs recherchées.
        Map<String, Object> params = Map.of(
                "firstName", firstName,
                "lastName", lastName
        );

        // queryForObject() récupère une seule valeur.
        Boolean exists = jdbcTemplate.queryForObject(
                sql,
                params,
                Boolean.class
        );

        return Boolean.TRUE.equals(exists);
    }
}
