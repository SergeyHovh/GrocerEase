package com.example.grocerease.controllers

import com.example.grocerease.exceptions.ResponseException
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

    @ExceptionHandler(ResponseException::class)
    fun clientNotFound(exception: ResponseException) =
        ResponseEntity.status(exception.httpStatus).body(exception.localizedMessage)

    @PostMapping("create")
    fun createClient(@RequestBody client: Client) =
        ResponseEntity.status(HttpStatus.CREATED).body(registrationService.saveClient(client))

    @GetMapping("{id}")
    fun findClient(@PathVariable id: Long) =
        ResponseEntity.status(HttpStatus.OK).body(registrationService.getClientById(id))

    @PatchMapping
    fun updateClient(@RequestBody updatedClient: Client) =
        ResponseEntity.status(HttpStatus.ACCEPTED).body(registrationService.updateClient(updatedClient))
}