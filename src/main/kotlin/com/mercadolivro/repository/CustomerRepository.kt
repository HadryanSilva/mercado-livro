package com.mercadolivro.repository

import com.mercadolivro.model.Customer
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface CustomerRepository : JpaRepository<Customer, Int> {
    fun findByNameContaining(name: String?): List<Customer>?
}