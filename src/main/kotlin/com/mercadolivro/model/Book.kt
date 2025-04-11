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

    @Enumerated(EnumType.STRING)
    var status: BookStatus? = null,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id")
    var customer: Customer? = null
)