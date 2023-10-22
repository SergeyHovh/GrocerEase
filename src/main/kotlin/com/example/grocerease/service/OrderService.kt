package com.example.grocerease.service

import com.example.grocerease.model.Basket
import com.example.grocerease.model.Client
import org.springframework.stereotype.Service

@Service
class OrderService(
    private val clientService: ClientService,
    private val basketService: BasketService
) {
    fun addBasketToClient(clientId: Long, basketId: Long): Pair<Client, Basket>? {
        val basket = basketService.findBasketById(basketId)
        val client = clientService.addBasketToClient(clientId, basket)
        return Pair(client, basket)
    }

}