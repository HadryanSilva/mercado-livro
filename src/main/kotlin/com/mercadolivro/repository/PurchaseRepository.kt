package com.mercadolivro.repository

import com.mercadolivro.model.Purchase
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface PurchaseRepository: JpaRepository<Purchase, Int> {
    fun findAllByCustomerId(customerId: Int): List<Purchase>
}