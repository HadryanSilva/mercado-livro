package com.mercadolivro.model

import com.mercadolivro.enums.CustomerStatus
import jakarta.persistence.*

@Entity
data class Customer(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int? = null,

    @Column(nullable = false)
    var name: String,

    @Column(nullable = false, unique = true)
    var email: String,

    @Enumerated(EnumType.STRING)
    var status: CustomerStatus
)