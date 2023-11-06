package com.example.grocerease.controllers

import com.example.grocerease.exceptions.ResponseException
import com.example.grocerease.model.Basket
import com.example.grocerease.model.Client
import com.example.grocerease.service.OrderService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/v1/order")
class OrderController(
    private val orderService: OrderService
) {

    @ExceptionHandler(ResponseException::class)
    fun clientNotFound(exception: ResponseException) =
        ResponseEntity.status(exception.httpStatus).body(exception.localizedMessage)

    @PostMapping("/create")
    fun createOrder(
        @RequestParam(name = "clientId") clientId: Long,
        @RequestParam(name = "basketId") basketId: Long
    ): ResponseEntity<Client> =
        ResponseEntity.status(HttpStatus.CREATED).body(orderService.addBasketToClient(clientId, basketId))

    @PostMapping("/add-product")
    fun addProduct(
        @RequestParam(name = "productId") productId: Long,
        @RequestParam(name = "basketId") basketId: Long,
        @RequestParam(name = "amount") amount: Double,
    ): ResponseEntity<Basket> =
        ResponseEntity.status(HttpStatus.CREATED).body(orderService.addProductToBasket(productId, basketId, amount))

}