package com.example.grocerease.model

import com.fasterxml.jackson.annotation.JsonBackReference
import com.fasterxml.jackson.annotation.JsonManagedReference
import jakarta.persistence.*

@Entity
@Table(name = "baskets")
data class Basket(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long,

    @ManyToOne
    @JoinColumn(name = "client_id")
    @JsonBackReference
    var client: Client? = null,

    @OneToMany(
        cascade = [CascadeType.ALL],
        mappedBy = "basket"
    )
    @JsonManagedReference
    val products: MutableList<BasketProduct> = mutableListOf()
) {
    fun addProduct(product: Product, amount: Double) {
        val basketProduct = products.firstOrNull { it.product == product }
        if (basketProduct == null) {
            products.add(BasketProduct(0, this, product, amount))
            return
        }
        basketProduct.amount += amount
    }

    fun removeProduct(product: Product) = products.removeIf { it.product == product }

    fun removeProductAmount(product: Product, amount: Double) {
        val basketProduct = products.firstOrNull { it.product == product } ?: return
        basketProduct.amount -= amount
        if (basketProduct.amount <= 0) {
            removeProduct(product)
        }
    }

    fun calculateTotalPrice(): Double = products.sumOf {
        it.amount * it.product.price
    }

    override fun toString(): String {
        return "Basket(id=$id, client=${client?.id})"
    }
}
