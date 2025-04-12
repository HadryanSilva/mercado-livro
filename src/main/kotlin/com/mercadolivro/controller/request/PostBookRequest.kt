package com.mercadolivro.controller.request

import com.fasterxml.jackson.annotation.JsonAlias
import com.mercadolivro.enums.BookStatus
import jakarta.validation.constraints.NotBlank
import org.jetbrains.annotations.NotNull
import java.math.BigDecimal

data class PostBookRequest (
    @field:NotBlank(message = "Nome não pode ser vazio")
    var name: String,

    @field:NotNull
    var price: BigDecimal,

    var status: BookStatus? = null,

    @field:NotNull
    @JsonAlias("customer_id")
    var customerId: Int? = null
)