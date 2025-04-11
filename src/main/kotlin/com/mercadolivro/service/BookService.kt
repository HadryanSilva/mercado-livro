package com.mercadolivro.service

import com.mercadolivro.model.Book
import com.mercadolivro.repository.BookRepository
import org.springframework.stereotype.Service

@Service
class BookService(
    val bookRepository: BookRepository
) {

    fun findByName(name: String?): List<Book> {
        return bookRepository.findByNameContaining(name) ?: emptyList()
    }

    fun findAll(): List<Book> {
        return bookRepository.findAll()
    }

    fun create(book: Book): Book {
        return bookRepository.save(book)
    }

    fun update(book: Book) {
        if (!bookRepository.existsById(book.id!!)) {
            throw Exception("Book not found")
        }
        bookRepository.save(book)
    }

    fun delete(id: Int) {
        bookRepository.deleteById(id)
    }
}