package com.mercadolivro.repository

import com.mercadolivro.model.Book
import org.springframework.data.jpa.repository.JpaRepository

interface BookRepository: JpaRepository<Book, Int> {
    fun findByNameContaining(name: String?): List<Book>?
}