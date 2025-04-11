package com.mercadolivro.service

import com.mercadolivro.controller.response.BookResponse
import com.mercadolivro.enums.BookStatus
import com.mercadolivro.extension.toResponse
import com.mercadolivro.model.Book
import com.mercadolivro.repository.BookRepository
import org.springframework.stereotype.Service

@Service
class BookService(
    val bookRepository: BookRepository
) {

    fun findAllActive(): List<BookResponse> {
        return bookRepository.findByStatus(BookStatus.ACTIVE)?.map { it.toResponse() } ?: emptyList()
    }

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
        val bookToUpdate = bookRepository.findById(book.id!!).get()
        bookToUpdate.name = book.name
        bookToUpdate.price = book.price
        bookToUpdate.status = book.status
        bookRepository.save(book)
    }

    fun delete(id: Int) {
        if (!bookRepository.existsById(id)) {
            throw Exception("Book not found")
        }
        bookRepository.deleteById(id)
    }
}