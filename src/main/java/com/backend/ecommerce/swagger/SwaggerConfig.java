/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.backend.ecommerce.swagger;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.http.HttpHeaders;

/**
 *
 * @author balde
 */
@OpenAPIDefinition(
        info = @Info(
                title = "API E-Commerce Apex Store",
                description = "La API Rest de Apex Store es una solución backend robusta diseñada para gestionar las operaciones de un e-commerce. Esta API permite a los usuarios interactuar con diversos recursos de la tienda online de manera segura y eficiente, implementando Spring Security para proteger los endpoints.",
                version = "1.0.0",
                contact = @Contact(
                        name = "Jhossymar Balderrama Rocha",
                        url = "https://jhossymarbalderrama-portafolio.web.app/",
                        email = "jhossymarbalderrama@gmail.com"
                )
        ),
        servers = {
            @Server(
                    description = "Dev Server",
                    url = "http://localhost:8080"
            ),
            @Server(
                    description = "Prod Server",
                    url = "https://apexstore-ecommerce-backend.onrender.com"
            )
        },
        security = @SecurityRequirement(
                name = "Security Token"
        )
)
@SecurityScheme(
        name = "Security Token",
        description = "Access Token for my API",
        type = SecuritySchemeType.HTTP,
        paramName = HttpHeaders.AUTHORIZATION, 
        in = SecuritySchemeIn.HEADER,
        scheme = "bearer",
        bearerFormat = "JWT"
)
public class SwaggerConfig {
}
