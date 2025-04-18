package com.mercadolivro.controller.request

import com.fasterxml.jackson.annotation.JsonAlias
import jakarta.validation.constraints.Positive
import org.jetbrains.annotations.NotNull

data class PostPurchaseRequest(
    @field:NotNull
    @field:Positive
    @field:JsonAlias("customer_id")
    val customerId: Int,

    @field:NotNull
    @field:JsonAlias("book_ids")
    val bookIds: Set<Int>,
)