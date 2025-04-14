package com.mercadolivro.security

import com.mercadolivro.enums.CustomerStatus
import com.mercadolivro.model.Customer
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails

class UserDetailsCustom(
    val customer: Customer
): UserDetails {

    val id = customer.id

    override fun getAuthorities(): MutableCollection<out GrantedAuthority> {
        return customer.roles
            .map { SimpleGrantedAuthority(it.description) }
            .toMutableList()
    }

    override fun getPassword(): String {
        return customer.password
    }

    override fun getUsername(): String {
        return customer.id.toString()
    }

    override fun isEnabled(): Boolean = customer.status == CustomerStatus.ACTIVE
}