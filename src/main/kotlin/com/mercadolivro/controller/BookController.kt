package com.mercadolivro.controller

import com.mercadolivro.controller.request.PostBookRequest
import com.mercadolivro.controller.request.PutBookRequest
import com.mercadolivro.controller.response.BookResponse
import com.mercadolivro.extension.toModel
import com.mercadolivro.extension.toResponse
import com.mercadolivro.model.Customer
import com.mercadolivro.service.BookService
import com.mercadolivro.service.CustomerService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/v1/book")
class BookController(
    val customerService: CustomerService,
    val bookService: BookService
) {

    @GetMapping("/list")
    fun findAll(): ResponseEntity<List<BookResponse>> {
        val books = bookService.findAll().map { it.toResponse() }
        return ResponseEntity.ok(books)
    }

    @GetMapping("/active")
    fun findAllActive(): ResponseEntity<List<BookResponse>> {
        val books = bookService.findAllActive()
        return ResponseEntity.ok(books)
    }

    @GetMapping("/{id}")
    fun findById(@PathVariable id: Int): ResponseEntity<BookResponse> {
        val book = bookService.findById(id).toResponse()
        return ResponseEntity.ok(book)
    }

    @GetMapping
    fun findByName(@RequestParam("name") name: String?): ResponseEntity<List<BookResponse>> {
        val books = bookService.findByName(name).map { it.toResponse() }
        return ResponseEntity.ok(books)
    }

    @PostMapping
    fun create(@RequestBody request: PostBookRequest): ResponseEntity<BookResponse> {
        val customer = customerService.getById(request.customerId)
        val response = bookService.create(request.toModel(customer)).toResponse()
        return ResponseEntity.status(HttpStatus.CREATED).body(response)
    }

    @PutMapping
    fun update(@RequestBody request: PutBookRequest): ResponseEntity<Void> {
        val bookSaved = bookService.findById(request.id);
        bookService.update(request.toModel(bookSaved))
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build()
    }

    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: Int): ResponseEntity<Void> {
        bookService.delete(id)
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build()
    }

}