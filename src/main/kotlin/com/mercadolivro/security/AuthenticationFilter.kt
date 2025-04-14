package com.mercadolivro.security

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.mercadolivro.controller.request.LoginRequest
import com.mercadolivro.enums.Errors
import com.mercadolivro.exception.AuthenticationException
import com.mercadolivro.repository.CustomerRepository
import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter

class AuthenticationFilter(
    authenticationManager: AuthenticationManager,
    private val customerRepository: CustomerRepository,
    private val jwtUtil: JWTUtil
): UsernamePasswordAuthenticationFilter(authenticationManager) {

    override fun attemptAuthentication(request: HttpServletRequest?, response: HttpServletResponse?): Authentication {
        try {
            val loginRequest = jacksonObjectMapper().readValue(request!!.inputStream, LoginRequest::class.java)
            val id = customerRepository.findByEmail(loginRequest.email)?.id
            val authToken = UsernamePasswordAuthenticationToken(id, loginRequest.password)
            return authenticationManager.authenticate(authToken)
        } catch (e: Exception) {
            throw AuthenticationException(Errors.ML997.message.format(e.message), Errors.ML997.code)
        }
    }

    override fun successfulAuthentication(
        request: HttpServletRequest?,
        response: HttpServletResponse?,
        chain: FilterChain?,
        authResult: Authentication
    ) {
        val id = (authResult.principal as UserDetailsCustom).id!!
        val token = jwtUtil.generateToken(id)
        response?.addHeader("Authorization", "Bearer $token")
    }

}