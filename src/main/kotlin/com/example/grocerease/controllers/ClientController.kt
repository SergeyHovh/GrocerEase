package com.example.grocerease.controllers

import com.example.grocerease.exceptions.ResponseException
import com.example.grocerease.model.Client
import com.example.grocerease.service.ClientService
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/v1/clients")
@CrossOrigin
class ClientController(
    private val clientService: ClientService
) {

    private val logger: Logger = LoggerFactory.getLogger(ClientController::class.java)

    @ExceptionHandler(ResponseException::class)
    fun clientNotFound(exception: ResponseException) =
        ResponseEntity.status(exception.httpStatus).body(exception.localizedMessage)

    @PostMapping
    fun createClient(@RequestBody client: Client) =
        ResponseEntity.status(HttpStatus.CREATED).body(clientService.saveClient(client))

    @GetMapping
    fun getAllClients(): ResponseEntity<List<Client>> {
        logger.info("getAllClients")
        return ResponseEntity.ok().body(clientService.findAllClients())
    }

    @GetMapping("/{id}")
    fun findClient(@PathVariable(name = "id") id: Long) =
        ResponseEntity.ok().body(clientService.findClientById(id))

    @PatchMapping
    fun updateClient(@RequestBody updatedClient: Client) =
        ResponseEntity.accepted().body(clientService.updateClient(updatedClient))
}