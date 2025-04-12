package com.mercadolivro.model

import com.mercadolivro.enums.BookStatus
import com.mercadolivro.enums.Errors
import com.mercadolivro.exception.BadRequestException
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
                throw BadRequestException(Errors.ML202.message.format(this.id), Errors.ML202.code)
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