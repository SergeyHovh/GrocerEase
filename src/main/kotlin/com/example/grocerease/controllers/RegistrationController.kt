package com.example.grocerease.controllers

import com.example.grocerease.exceptions.ClientNotFoundException
import com.example.grocerease.model.Client
import com.example.grocerease.service.RegistrationService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/v1/registration")
class RegistrationController(
    private val registrationService: RegistrationService
) {

    @ExceptionHandler(ClientNotFoundException::class)
    fun clientNotFound(exception: ClientNotFoundException): ResponseEntity<String> {
        return ResponseEntity
            .status(HttpStatus.NOT_FOUND)
            .body(exception.localizedMessage)
    }

    @PostMapping("create")
    fun createClient(@RequestBody client: Client): ResponseEntity<Client> {
        val saveClient = registrationService.saveClient(client)
        return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(saveClient)
    }

    @GetMapping("{id}")
    fun findClient(@PathVariable id: Long): ResponseEntity<Client> {
        val clientById = registrationService.getClientById(id)
        return ResponseEntity
            .status(200)
            .body(clientById)
    }
}