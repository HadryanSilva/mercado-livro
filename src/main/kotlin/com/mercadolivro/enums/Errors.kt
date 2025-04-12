package com.mercadolivro.enums

enum class Errors(val code: String, val message: String) {
    ML101("ML-101", "Customer with id %s not found"),
    ML102("ML-102", "Customer with id %s is not active"),
    ML201("ML-201", "Book with id %s not found"),
    ML202("ML-202", "Book with id %s cannot be updated because is deleted or canceled"),
    ML999("ML-999", "Internal server error: NullPointerException"),
}