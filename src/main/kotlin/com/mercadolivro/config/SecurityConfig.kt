package com.mercadolivro.config

import com.mercadolivro.repository.CustomerRepository
import com.mercadolivro.security.AuthenticationFilter
import com.mercadolivro.security.AuthorizationFilter
import com.mercadolivro.security.JWTUtil
import com.mercadolivro.service.UserDetailsCustomService
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.SecurityFilterChain

@Configuration
@EnableWebSecurity
class SecurityConfig(
    private val customerRepository: CustomerRepository,
    private val authenticationConfiguration: AuthenticationConfiguration,
    private val userDetailsCustomService: UserDetailsCustomService,
    private val jwtUtil: JWTUtil
) {

    private val PUBLIC_POST_ENDPOINTS = arrayOf(
        "/api/v1/customer"
    )

    @Bean
    fun securityFilterChain(http: HttpSecurity): SecurityFilterChain {
        http
            .csrf { it.disable() }
            .authorizeHttpRequests {
                it.requestMatchers("/v3/api-docs/**", "/swagger-ui/**").permitAll()
                it.requestMatchers(HttpMethod.POST, *PUBLIC_POST_ENDPOINTS).permitAll()
                it.anyRequest().authenticated()
            }
            .addFilter(AuthenticationFilter(authenticationManager(), customerRepository, jwtUtil))
            .addFilter(AuthorizationFilter(authenticationManager(), userDetailsCustomService, jwtUtil))
            .sessionManagement { it.sessionCreationPolicy(SessionCreationPolicy.STATELESS) }
        return http.build()
    }

    @Bean
    fun authenticationManager(): AuthenticationManager {
        return authenticationConfiguration.authenticationManager
    }

    @Bean
    fun passwordEncoder(): PasswordEncoder {
        return BCryptPasswordEncoder()
    }

}