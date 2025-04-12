package com.mercadolivro.controller.mapper

import com.mercadolivro.controller.request.PostPurchaseRequest
import com.mercadolivro.controller.response.PurchaseResponse
import com.mercadolivro.model.Purchase
import com.mercadolivro.service.BookService
import com.mercadolivro.service.CustomerService
import org.springframework.stereotype.Service

@Service
class PurchaseMapper(
    private val customerService: CustomerService,
    private val bookService: BookService,
) {
    fun toModel(request: PostPurchaseRequest): Purchase {
        val customer = customerService.findById(request.customerId)
        val books = bookService.findAllById(request.bookIds)

        return Purchase(
            customer = customer,
            books = books,
            price = books.sumOf { it.price }
        )
    }

    fun toResponse(purchase: Purchase): PurchaseResponse {
        return PurchaseResponse(
            id = purchase.id,
            customer = purchase.customer,
            books = purchase.books,
            price = purchase.price,
            createdAt = purchase.createdAt
        )
    }
}