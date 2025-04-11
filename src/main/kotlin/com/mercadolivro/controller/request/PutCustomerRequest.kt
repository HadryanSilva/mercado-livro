package com.mercadolivro.controller.request

data class PutCustomerRequest(
    val id: Int,
    val name: String,
    val email: String,
)