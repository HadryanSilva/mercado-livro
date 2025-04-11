package com.mercadolivro.controller.request

import com.mercadolivro.enums.BookStatus
import java.math.BigDecimal

data class PutBookRequest (
    var id: Int,
    var name: String,
    var price: BigDecimal?,
    var customerId: Int,
)