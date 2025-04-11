package com.mercadolivro.model

import com.mercadolivro.enums.BookStatus
import jakarta.persistence.*
import java.math.BigDecimal

@Entity
data class Book(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int? = null,

    @Column(nullable = false)
    var name: String,

    @Column(nullable = false)
    var price: BigDecimal,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id")
    var customer: Customer? = null
) {

    @Enumerated(EnumType.STRING)
    var status: BookStatus? = null
        set(value) {
            if (field == BookStatus.CANCELED || field == BookStatus.DELETED) {
                throw Exception("Book cannot be updated")
            }
            field = value
        }

    constructor(
        id: Int?,
        name: String,
        price: BigDecimal,
        customer: Customer?,
        status: BookStatus?
    ) : this(id, name, price, customer) {
        this.status = status
    }
}