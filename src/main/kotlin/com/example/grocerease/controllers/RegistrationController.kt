package com.example.grocerease.controllers

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

    @PostMapping("create")
    fun createClient(@RequestBody client: Client): ResponseEntity<String> {
        val saveClient = registrationService.saveClient(client)
        return ResponseEntity
            .status(HttpStatus.CREATED)
            .body("id: ${saveClient.id}")
    }

    @GetMapping("{id}")
    fun findClient(@PathVariable id: Long): ResponseEntity<String> {
        return try {
            val clientById = registrationService.getClientById(id)
            ResponseEntity
                .status(200)
                .body(clientById!!.username)
        } catch (e: Exception) {
            ResponseEntity
                .status(404)
                .body("not found")
        }
    }
}