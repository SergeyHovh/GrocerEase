package com.example.grocerease.exceptions

class ClientNotFoundException(message: String) : RuntimeException(message) {
    constructor(clientId: Long) : this("Client with ID $clientId not found")
}
