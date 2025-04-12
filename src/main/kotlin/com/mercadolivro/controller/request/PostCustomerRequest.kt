package com.mercadolivro.controller.request

import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotBlank

data class PostCustomerRequest(

    @field:NotBlank(message = "Nome não pode ser vazio")
    val name: String,

    @field:Email(message = "Email deve ser válido")
    @field:NotBlank(message = "Email não pode ser vazio")
    val email: String,
)