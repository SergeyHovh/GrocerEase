package com.example.grocerease.exceptions

import org.springframework.http.HttpStatus

class ClientNotFoundException(message: String) : ResponseException(HttpStatus.NOT_FOUND, message) {
    constructor(clientId: Long) : this("Client with ID $clientId not found")
}
