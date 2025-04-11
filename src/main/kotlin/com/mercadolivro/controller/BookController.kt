package com.mercadolivro.controller

import com.mercadolivro.controller.request.PostBookRequest
import com.mercadolivro.controller.response.BookResponse
import com.mercadolivro.extension.toModel
import com.mercadolivro.extension.toResponse
import com.mercadolivro.service.BookService
import com.mercadolivro.service.CustomerService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/book")
class BookController(
    val customerService: CustomerService,
    val bookService: BookService
) {

    @PostMapping
    fun create(@RequestBody request: PostBookRequest): ResponseEntity<BookResponse> {
        val customer = customerService.getById(request.customerId)
        val response = bookService.create(request.toModel(customer)).toResponse()
        return ResponseEntity.status(HttpStatus.CREATED).body(response)
    }

}