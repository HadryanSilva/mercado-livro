package com.mercadolivro.extension

import com.mercadolivro.controller.request.PostBookRequest
import com.mercadolivro.controller.request.PostCustomerRequest
import com.mercadolivro.controller.request.PutBookRequest
import com.mercadolivro.controller.request.PutCustomerRequest
import com.mercadolivro.controller.response.BookResponse
import com.mercadolivro.controller.response.CustomerResponse
import com.mercadolivro.enums.BookStatus
import com.mercadolivro.enums.CustomerStatus
import com.mercadolivro.model.Book
import com.mercadolivro.model.Customer

fun PostCustomerRequest.toModel(): Customer {
    return Customer(
        name = this.name,
        email = this.email,
        status = CustomerStatus.ACTIVE
    )
}

fun PutCustomerRequest.toModel(previousValue: Customer): Customer {
    return Customer(
        id = previousValue.id,
        name = this.name,
        email = this.email,
        status = this.status
    )
}

fun Customer.toResponse(): CustomerResponse {
    return CustomerResponse(
        id = this.id,
        name = this.name,
        email = this.email,
        status = this.status
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

fun PutBookRequest.toModel(previousValue: Book): Book {
    return Book(
        id = previousValue.id,
        name = this.name,
        price = this.price ?: previousValue.price,
        status = previousValue.status,
        customer = previousValue.customer
    )
}

fun Book.toResponse(): BookResponse {
    return BookResponse(
        id = this.id,
        name = this.name,
        price = this.price,
        status = this.status,
        customer = this.customer
    )
}

