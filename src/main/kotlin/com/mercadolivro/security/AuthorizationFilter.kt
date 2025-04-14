package com.mercadolivro.security

import com.mercadolivro.enums.Errors
import com.mercadolivro.exception.AuthenticationException
import com.mercadolivro.service.UserDetailsCustomService
import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.http.HttpHeaders.AUTHORIZATION
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter

class AuthorizationFilter(
    authenticationManager: AuthenticationManager,
    private val userDetailsCustomService: UserDetailsCustomService,
    private val jwtUtil: JWTUtil
):
    BasicAuthenticationFilter(authenticationManager) {
    override fun doFilterInternal(request: HttpServletRequest,
                                  response: HttpServletResponse,
                                  chain: FilterChain
    ) {
        val authorization = request.getHeader(AUTHORIZATION)
        if (authorization != null && authorization.startsWith("Bearer ")) {
            val auth = getAuthentication(authorization.split(" ")[1])
            SecurityContextHolder.getContext().authentication = auth
        }

        chain.doFilter(request, response)
    }

    private fun getAuthentication(token: String): UsernamePasswordAuthenticationToken {
        if (!jwtUtil.isValidToken(token)) {
            throw AuthenticationException(Errors.ML997.message, Errors.ML997.code)
        }

        val subject = jwtUtil.getSubject(token)
        val customer = userDetailsCustomService.loadUserByUsername(subject)
        return UsernamePasswordAuthenticationToken(subject, null, customer.authorities)
    }
}