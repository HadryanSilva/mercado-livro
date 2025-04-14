package com.mercadolivro.security

import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.security.Keys
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import java.nio.charset.StandardCharsets
import java.security.Key
import java.util.*


@Component
class JWTUtil {

    @Value("\${jwt.secret}")
    private val secretKey: String? = null

    @Value("\${jwt.expiration}")
    private val expirationTime: Long? = null

    fun generateToken(id: Int): String {
        val key: Key = Keys.hmacShaKeyFor(secretKey!!.toByteArray(StandardCharsets.UTF_8))

        return Jwts.builder()
            .claims()
            .subject(id.toString())
            .issuedAt(Date(System.currentTimeMillis()))
            .expiration(Date(System.currentTimeMillis() + expirationTime!!))
            .and()
            .signWith(key)
            .compact()
    }

    fun isValidToken(token: String): Boolean {
        val claims = getClaims(token)
        return !(claims.expiration == null || Date().after(claims.expiration));
    }

    private fun getClaims(token: String): Claims {
        val key: Key = Keys.hmacShaKeyFor(secretKey!!.toByteArray(StandardCharsets.UTF_8))

        return Jwts.parser()
            .setSigningKey(key)
            .build()
            .parseClaimsJws(token)
            .body
    }

    fun getSubject(token: String): String {
        return getClaims(token).subject
    }

}