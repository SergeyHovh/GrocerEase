package com.example.grocerease.service

import com.example.grocerease.exceptions.BasketAlreadyExistsException
import com.example.grocerease.exceptions.BasketNotFoundException
import com.example.grocerease.model.Basket
import com.example.grocerease.repository.BasketRepository
import org.springframework.stereotype.Service

@Service
class BasketService(
    private val repository: BasketRepository,
) {
    fun findBasketById(basketId: Long): Basket =
        repository.findById(basketId).orElseThrow { BasketNotFoundException(basketId) }

    fun saveBasket(basket: Basket): Basket {
        repository.findById(basket.id).ifPresent { throw BasketAlreadyExistsException(basket.id) }
        return repository.save(basket)
    }

    fun updateBasket(basket: Basket): Basket {
        findBasketById(basket.id)
        return repository.save(basket)
    }
}