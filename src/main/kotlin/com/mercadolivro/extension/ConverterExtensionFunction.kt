package com.mercadolivro.extension

import com.mercadolivro.controller.request.PostBookRequest
import com.mercadolivro.controller.request.PostCustomerRequest
import com.mercadolivro.controller.request.PutCustomerRequest
import com.mercadolivro.controller.response.BookResponse
import com.mercadolivro.controller.response.CustomerResponse
import com.mercadolivro.enums.BookStatus
import com.mercadolivro.model.Book
import com.mercadolivro.model.Customer

fun PostCustomerRequest.toModel(): Customer {
    return Customer(
        name = this.name,
        email = this.email,
    )
}

fun PutCustomerRequest.toModel(id: Int): Customer {
    return Customer(
        id = id,
        name = this.name,
        email = this.email,
    )
}

fun Customer.toResponse(): CustomerResponse {
    return CustomerResponse(
        id = this.id,
        name = this.name,
        email = this.email,
    )
}

fun PostBookRequest.toModel(customer: Customer?): Book {
    return Book(
        name = this.name,
        price = this.price,
        status = BookStatus.ACTIVE,
        customer = customer
    )
}

fun Book.toResponse(): BookResponse {
    return BookResponse(
        id = this.id,
        name = this.name,
        price = this.price,
        status = this.status
    )
}