package com.example.grocerease.exceptions

import org.springframework.http.HttpStatus

class BasketAlreadyExistsException(message: String) : ResponseException(HttpStatus.CONFLICT, message) {
    constructor(basketId: Long) : this("Basket with ID $basketId already exists")
}