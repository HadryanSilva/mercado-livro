package com.mercadolivro.enums

enum class Errors(val code: String, val message: String) {
    ML101("ML-101", "Customer with id %s not found"),
    ML102("ML-102", "Customer with id %s is not active"),
    ML201("ML-201", "Book with id %s not found"),
    ML202("ML-202", "Book with id %s cannot be updated because is deleted or canceled"),
    ML203("ML-203", "Book with id %s is not available for purchase"),
    ML996("ML-996", "User not found"),
    ML997("ML-997", "Authentication failed: %s"),
    ML998("ML-998", "Invalid Request"),
    ML999("ML-999", "Internal server error: NullPointerException"),
}