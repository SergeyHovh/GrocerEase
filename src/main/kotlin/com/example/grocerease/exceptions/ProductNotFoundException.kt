package com.example.grocerease.exceptions

import org.springframework.http.HttpStatus

class ProductNotFoundException(message: String) : ResponseException(HttpStatus.NOT_FOUND, message) {
    constructor(productId: Long) : this("Product with ID $productId not found")
}