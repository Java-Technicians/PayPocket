package com.alkemy.paypocket.configurations;

import com.alkemy.paypocket.PaypocketApplication;
import com.alkemy.paypocket.entities.Crypto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.media.Schema;
import org.springdoc.core.customizers.OpenApiCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;
import java.util.TreeMap;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenApi() {
        return new OpenAPI().info(new Info()
                .title("Paypocket")
                .version("Version 1.0")
                .description("API de una billetera virtual")
                .termsOfService("https//swagger.io/terms/")
                .license(new License()
                        .name("Apache 2.0")
                        .url("http://springdoc.org"))
                .contact(new Contact()
                        .name("Karen Lettieri - Nahuel Alderete - Braiton Hemsouvanh - Dante Guiliano - Ignacio Herrera")
                        .url("https://github.com/Java-Technicians/PayPocket")));
    }

/*
    // Ordena en orden alfabetico los Schemas.
    @Bean
    public OpenApiCustomizer sortSchemasAlphabetically() {
        return openApi -> {
            Map<String, Schema> schemas = openApi.getComponents().getSchemas();
            openApi.getComponents().setSchemas(new TreeMap<>(schemas));
        };
    }

 */
}
