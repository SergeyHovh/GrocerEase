package com.example.grocerease.exceptions

import org.springframework.http.HttpStatus

class ClientAlreadyExistsException(message: String) : ResponseException(HttpStatus.CONFLICT, message) {
    constructor(clientId: Long) : this("Client with ID $clientId already exists")
}