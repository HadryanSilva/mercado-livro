package com.mercadolivro.controller.request

import com.mercadolivro.enums.CustomerStatus
import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotBlank
import org.jetbrains.annotations.NotNull

data class PutCustomerRequest(
    @field:NotNull
    val id: Int,

    @field:NotBlank(message = "Nome não pode ser vazio")
    val name: String,

    @field:Email(message = "Email deve ser válido")
    @field:NotBlank(message = "Email não pode ser vazio")
    val email: String,

    @field:NotBlank(message = "Senha não pode ser vazio")
    val password: String,

    @field:NotNull
    val status: CustomerStatus
)