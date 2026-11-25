
package com.AppliBanque.Appli.infrastructure.application.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("API AppliBanque")
                        .version("1.0.0")
                        .description(
                                "API REST de gestion des clients bancaires. " +
                                        "Elle permet de créer et consulter les clients."
                        ));
    }
}
