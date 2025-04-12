package com.mercadolivro.service

import com.mercadolivro.controller.response.BookResponse
import com.mercadolivro.enums.BookStatus
import com.mercadolivro.enums.CustomerStatus
import com.mercadolivro.exception.NotFoundException
import com.mercadolivro.extension.toResponse
import com.mercadolivro.model.Book
import com.mercadolivro.model.Customer
import com.mercadolivro.repository.BookRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service

@Service
class BookService(
    val bookRepository: BookRepository
) {

    fun findAllActive(): List<BookResponse> {
        return bookRepository.findByStatus(BookStatus.ACTIVE)?.map { it.toResponse() } ?: emptyList()
    }

    fun findById(id: Int?): Book {
        return bookRepository.findById(id!!).orElseThrow { NotFoundException("Book not found") }
    }

    fun findByName(name: String?): List<Book> {
        return bookRepository.findByNameContaining(name) ?: emptyList()
    }

    fun findAll(pageable: Pageable): Page<Book> {
        return bookRepository.findAll(pageable)
    }

    fun create(book: Book): Book {
        if (book.customer?.status != CustomerStatus.ACTIVE) {
            throw Exception("Customer is not active")
        }
        return bookRepository.save(book)
    }

    fun update(book: Book) {
        if (!bookRepository.existsById(book.id!!)) {
            throw Exception("Book not found")
        }
        bookRepository.save(book)
    }

    fun delete(id: Int) {
        val book = bookRepository.findById(id).orElseThrow { NotFoundException("Book not found") }
        book.status = BookStatus.CANCELED
        bookRepository.save(book)
    }

    fun deleteByCustomer(customer: Customer) {
        val books = bookRepository.findByCustomer(customer)
        books.forEach { it.status = BookStatus.DELETED }
        bookRepository.saveAll(books);
    }

}