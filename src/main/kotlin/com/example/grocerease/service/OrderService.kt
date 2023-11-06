package com.example.grocerease.service

import com.example.grocerease.model.Basket
import com.example.grocerease.model.Client
import org.springframework.stereotype.Service

@Service
class OrderService(
    private val clientService: ClientService,
    private val basketService: BasketService,
    private val productService: ProductService
) {
    fun addBasketToClient(clientId: Long, basketId: Long): Client {
        val basket = basketService.findBasketById(basketId)
        val client = clientService.addBasketToClient(clientId, basket)
        return client
    }

    fun addProductToBasket(productId: Long, basketId: Long, amount: Double): Basket {
        val product = productService.findProduct(productId)
        val basket = basketService.findBasketById(basketId)
        basket.addProduct(product, amount)
        basketService.updateBasket(basket)
        return basket
    }

}