package com.mercadolivro.controller.request

import com.mercadolivro.enums.CustomerStatus

data class PutCustomerRequest(
    val id: Int,
    val name: String,
    val email: String,
    val status: CustomerStatus
)