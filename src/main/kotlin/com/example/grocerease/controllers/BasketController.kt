package com.example.grocerease.controllers

import com.example.grocerease.exceptions.ResponseException
import com.example.grocerease.model.Basket
import com.example.grocerease.service.BasketService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/v1/baskets")
@CrossOrigin
class BasketController(
    private val basketService: BasketService
) {

    @ExceptionHandler(ResponseException::class)
    fun clientNotFound(exception: ResponseException) =
        ResponseEntity.status(exception.httpStatus).body(exception.localizedMessage)

    @GetMapping("/{id}")
    fun getBasket(@PathVariable(name = "id") id: Long): ResponseEntity<Basket> =
        ResponseEntity.ok().body(basketService.findBasketById(id))

    @PostMapping
    fun createBasket(@RequestBody basket: Basket) =
        ResponseEntity.status(HttpStatus.CREATED).body(basketService.saveBasket(basket))

    @PatchMapping
    fun updateBasket(@RequestBody updatedBasket: Basket) =
        ResponseEntity.accepted().body(basketService.updateBasket(updatedBasket))
}