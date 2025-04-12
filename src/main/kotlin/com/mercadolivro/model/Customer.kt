package com.mercadolivro.model

import com.mercadolivro.enums.Profile
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

    @Column(nullable = false)
    var password: String,

    @Enumerated(EnumType.STRING)
    var status: CustomerStatus,

    @Column(name = "role")
    @CollectionTable(
        name = "customer_roles",
        joinColumns = [JoinColumn(name = "customer_id")]
    )
    @ElementCollection(targetClass = Profile::class, fetch = FetchType.EAGER)
    @Enumerated(EnumType.STRING)
    var roles: Set<Profile> = setOf()
)