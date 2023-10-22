package com.example.grocerease.model

import com.fasterxml.jackson.annotation.JsonBackReference
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
    var client: Client? = null
) {
    override fun toString(): String {
        return "Basket(id=$id, client=${client?.id})"
    }
}
