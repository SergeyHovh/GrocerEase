package com.example.grocerease.controllers

import com.example.grocerease.model.Client
import com.example.grocerease.service.RegistrationService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.time.LocalDate

@RestController
@RequestMapping("/api/v1/registration")
class RegistrationController(
    private val registrationService: RegistrationService
) {

    @PostMapping("create")
    fun createClient(): ResponseEntity<String> {
        val id = (100_000L..999_999L).random()
        registrationService.saveClient(Client(id, "tmp", LocalDate.now()))
        return ResponseEntity.status(HttpStatus.CREATED).body("id: $id")
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