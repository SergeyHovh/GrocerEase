package com.example.grocerease.model

import com.fasterxml.jackson.annotation.JsonBackReference
import jakarta.persistence.*

@Entity
@Table(name = "basket_products")
data class BasketProduct(
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    val id: Long,

    @ManyToOne(
        cascade = [CascadeType.ALL]
    )
    @JoinColumn(name = "basket_id", foreignKey = ForeignKey(name = "fk_basket_product_basket"))
    @JsonBackReference
    val basket: Basket,

    @ManyToOne(
        cascade = [CascadeType.ALL],
    )
    @JoinColumn(name = "product_id", foreignKey = ForeignKey(name = "fk_basket_product_product"))
    val product: Product,

    @Column(name = "amount")
    var amount: Double
)