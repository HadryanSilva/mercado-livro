package com.mercadolivro.enums

enum class Errors(val code: String, val message: String) {
    ML101("ML-101", "Customer with id %s not found"),
    ML201("ML-201", "Book with id %s not found"),
    ML999("ML-999", "Internal server error: NullPointerException"),
}