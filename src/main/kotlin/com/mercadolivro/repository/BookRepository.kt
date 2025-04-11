package com.mercadolivro.repository

import com.mercadolivro.enums.BookStatus
import com.mercadolivro.model.Book
import com.mercadolivro.model.Customer
import org.springframework.data.jpa.repository.JpaRepository

interface BookRepository: JpaRepository<Book, Int> {
    fun findByNameContaining(name: String?): List<Book>?
    fun findByStatus(status: BookStatus): List<Book>?
    fun findByCustomer(customer: Customer): List<Book>
}