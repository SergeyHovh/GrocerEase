package com.example.grocerease.model

import jakarta.persistence.*

@Entity
@Table(name = "products")
data class Product(
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    val id: Long,

    @Column(name = "product_name")
    val productName: String,

    @Column(name = "price")
    val price: Double,
)