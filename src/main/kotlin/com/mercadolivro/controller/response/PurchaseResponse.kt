package com.mercadolivro.controller.response

import com.mercadolivro.model.Book
import com.mercadolivro.model.Customer
import java.math.BigDecimal
import java.time.LocalDateTime

data class PurchaseResponse (
    var id: Int? = null,
    var customer: Customer? = null,
    val books: List<Book>,
    val price: BigDecimal,
    val createdAt: LocalDateTime
)