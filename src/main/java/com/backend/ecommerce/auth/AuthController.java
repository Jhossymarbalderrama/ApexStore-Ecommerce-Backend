/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.backend.ecommerce.auth;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author balde
 */
@RestController
@CrossOrigin(origins = {"http://localhost:4200","https://e-commerce-ac291.web.app/"})
@RequestMapping("/auth")
@RequiredArgsConstructor
@Tag(name="Authentication", description="Controller for Authentication Users")
public class AuthController {
    
    private final AuthService authService;
    
    @PostMapping("login")
    @Operation(
        summary = "Login para Usuarios",
        description = "Authentificacion de Usuarios, retorna token de authentificacion",
        tags = {"Authentication"},
        requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                description = "Authentication request with username and password",
                required = true,
                content = @Content(
                        mediaType = "application/json",
                        schema = @Schema(implementation = LoginRequest.class)
                )
        ),
        responses = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Successful authentication",
                    content = @Content(
                            mediaType = "application/json"                            
                    )
            )
        }
    )
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest request)
    {
        return ResponseEntity.ok(authService.login(request));
    }

    @PostMapping("register")
    @Operation(
        summary = "Registro de Usuarios",
        description = "Registro para usuarios, retorna Object creado",
        tags = {"Register Users"},
        requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                description = "Register Users request with Data Users Required",
                required = true,
                content = @Content(
                        mediaType = "application/json",
                        schema = @Schema(implementation = RegisterRequest.class)
                )
        ),
        responses = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Successful register",
                    content = @Content(
                            mediaType = "application/json",    
                            schema = @Schema(implementation = RegisterRequest.class)
                    )
            )
        }
    )
    public ResponseEntity<AuthResponse> register(@RequestBody RegisterRequest request)
    {
        return ResponseEntity.ok(authService.register(request));
    }

}
