package com.example.grocerease.exceptions

import org.springframework.http.HttpStatus

class BasketNotFoundException(message: String) : ResponseException(HttpStatus.NOT_FOUND, message) {
    constructor(basketId: Long) : this("Basket with ID $basketId not found")
}