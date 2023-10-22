package com.example.grocerease.model

import com.fasterxml.jackson.annotation.JsonManagedReference
import jakarta.persistence.*
import java.time.LocalDate

@Entity
@Table(name = "clients")
data class Client(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long,

    @Column(name = "username")
    val username: String,

    @Column(name = "dob")
    val dateOfBirth: LocalDate,

    @OneToMany(mappedBy = "client", cascade = [CascadeType.ALL], orphanRemoval = true)
    @JsonManagedReference
    var baskets: MutableList<Basket> = mutableListOf()
) {
    fun addBasket(basket: Basket) {
        baskets.add(basket)
        basket.client = this
    }

    fun removeBasket(basket: Basket) {
        baskets.remove(basket)
        basket.client = null
    }
}