package com.example.grocerease.controllers

import com.example.grocerease.exceptions.ResponseException
import com.example.grocerease.model.Client
import com.example.grocerease.service.ClientService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/v1/client")
class ClientController(
    private val clientService: ClientService
) {

    @ExceptionHandler(ResponseException::class)
    fun clientNotFound(exception: ResponseException) =
        ResponseEntity.status(exception.httpStatus).body(exception.localizedMessage)

    @PostMapping("create")
    fun createClient(@RequestBody client: Client) =
        ResponseEntity.status(HttpStatus.CREATED).body(clientService.saveClient(client))

    @GetMapping
    fun findClient(@RequestParam(name = "id") id: Long) =
        ResponseEntity.ok().body(clientService.findClientById(id))

    @PatchMapping
    fun updateClient(@RequestBody updatedClient: Client) =
        ResponseEntity.accepted().body(clientService.updateClient(updatedClient))
}