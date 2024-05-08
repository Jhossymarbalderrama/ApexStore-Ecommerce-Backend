package com.backend.ecommerce.JwtConfig;

import com.backend.ecommerce.jwt.JwtAuthenticationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 *
 * @author balde
 */
@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
    /**
     * *
     * Method Filter Routers csrf > [En este method desactivo los metodos de
     * seguridad csrf de spring-security] autorizeHttpRequests > requestMatchers
     * > [Autorizo que el endpoint /auth/*todo* entre sin ninguna restriccion]
     * autorizeHttpRequests > anyRequest > [Autorizo que los demas endpoint
     * tengan restriccion de autorizacion] formLogin > [Genera un Formulario de
     * Login por default] .formLogin(withDefaults()) user > user password >
     * default generated console run [cambia xd]
     *
     *
     * JWT [HEADER | PAYLOAD | SIGNATURE] HEADER > [TYPE TOKEN - TYPE ALGORITH]
     * PAYLOAD > [INFO LOGIN USER {MODEL}] SIGNATURE > [VERIFICAR INTEGRIDAD DEL
     * TOKEN]
     *
     *
     * HttpReques -> JwtAutentication(filter) -> Controller -> Service -> BD
     *
     * @param http
     * @return
     */
    
    private final JwtAuthenticationFilter jwtAuthenticationFilter;
    private final AuthenticationProvider authProvider;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception
    {
        return http
            .csrf(csrf -> csrf.disable())                
            .authorizeHttpRequests(authRequest ->
                                                authRequest
                                                    .requestMatchers("/auth/**").permitAll()
                                                    .requestMatchers("/api/product/list").permitAll()
                                                    .requestMatchers("/api/product/get/**").permitAll()
                                                    .requestMatchers("/api/user/get/**").permitAll()                                                                                                        
                                                    .anyRequest().authenticated())
            .sessionManagement(sessionManager->
                                                sessionManager 
                                                    .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                                                    .authenticationProvider(authProvider)
                                                    .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                                                    .build();                        
    }
    /*
    csrf
                .disable()
    csrf.ignoringRequestMatchers("/auth/**", "/api/product/list", "/api/product/get/**", "/api/user/get/**")
    
                .requestMatchers("/auth/**").permitAll()
                .requestMatchers("/api/product/list").permitAll()
                .requestMatchers("/api/product/get/**").permitAll()
                .requestMatchers("/api/user/get/**").permitAll()
    .anyRequest().authenticated()            
    */
}
