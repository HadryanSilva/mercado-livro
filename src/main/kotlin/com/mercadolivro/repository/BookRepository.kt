package com.mercadolivro.repository

import com.mercadolivro.enums.BookStatus
import com.mercadolivro.model.Book
import com.mercadolivro.model.Customer
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface BookRepository: JpaRepository<Book, Int> {
    override fun findAll(pageable: Pageable): Page<Book>
    fun findByNameContaining(name: String?): List<Book>?
    fun findByStatus(status: BookStatus): List<Book>?
    fun findByCustomer(customer: Customer): List<Book>
}