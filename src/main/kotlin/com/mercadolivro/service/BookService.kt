package com.mercadolivro.service

import com.mercadolivro.controller.response.BookResponse
import com.mercadolivro.enums.BookStatus
import com.mercadolivro.enums.CustomerStatus
import com.mercadolivro.enums.Errors
import com.mercadolivro.exception.BadRequestException
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
    private val bookRepository: BookRepository
) {

    fun findAllActive(): List<BookResponse> {
        return bookRepository.findByStatus(BookStatus.ACTIVE)?.map { it.toResponse() } ?: emptyList()
    }

    fun findById(id: Int?): Book {
        return bookRepository.findById(id!!).orElseThrow { NotFoundException(Errors.ML201.message.format(id),
            Errors.ML201.code) }
    }

    fun findByName(name: String?): List<Book> {
        return bookRepository.findByNameContaining(name) ?: emptyList()
    }

    fun findAll(pageable: Pageable): Page<Book> {
        return bookRepository.findAll(pageable)
    }

    fun findAllById(bookIds: Set<Int>): List<Book> {
        return bookRepository.findAllById(bookIds).toList()
    }

    fun create(book: Book): Book {
        if (book.customer?.status != CustomerStatus.ACTIVE) {
            throw BadRequestException(Errors.ML102.message.format(book.id), Errors.ML102.code)
        }
        return bookRepository.save(book)
    }

    fun update(book: Book) {
        val bookToUpdate = bookRepository.findById(book.id!!)
            .orElseThrow { NotFoundException(Errors.ML201.message.format(book.id), Errors.ML201.code) }
        bookToUpdate.name = book.name
        bookToUpdate.price = book.price
        bookToUpdate.status = book.status
        bookRepository.save(bookToUpdate)
    }

    fun delete(id: Int) {
        val book = bookRepository.findById(id).orElseThrow { NotFoundException(Errors.ML201.message.format(id),
            Errors.ML201.code) }
        book.status = BookStatus.CANCELED
        bookRepository.save(book)
    }

    fun deleteByCustomer(customer: Customer) {
        val books = bookRepository.findByCustomer(customer)
        books.forEach { it.status = BookStatus.DELETED }
        bookRepository.saveAll(books);
    }

    fun updateSoldBook(book: Book) {
        val bookToUpdate = bookRepository.findById(book.id!!)
            .orElseThrow { NotFoundException(Errors.ML201.message.format(book.id), Errors.ML201.code) }
        bookToUpdate.status = BookStatus.SOLD
        bookRepository.save(bookToUpdate)
    }

}