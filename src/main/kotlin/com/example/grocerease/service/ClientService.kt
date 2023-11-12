package com.example.grocerease.service

import com.example.grocerease.exceptions.BasketNotFoundException
import com.example.grocerease.exceptions.ClientAlreadyExistsException
import com.example.grocerease.exceptions.ClientNotFoundException
import com.example.grocerease.model.Basket
import com.example.grocerease.model.Client
import com.example.grocerease.repository.ClientRepository
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service

@Service
class ClientService(
    private val clientRepository: ClientRepository
) {

    private val logger: Logger = LoggerFactory.getLogger(ClientService::class.java)

    fun saveClient(client: Client): Client {
        clientRepository.findById(client.id).ifPresent { throw ClientAlreadyExistsException(client.id) }
        return clientRepository.save(client)
    }

    fun findClientById(id: Long): Client = clientRepository.findById(id).orElseThrow { ClientNotFoundException(id) }

    fun updateClient(client: Client): Client {
        findClientById(client.id)
        return clientRepository.save(client)
    }

    fun addBasketToClient(clientId: Long, basket: Basket): Client {
        val client = clientRepository.findById(clientId)
            .orElseThrow { ClientNotFoundException(clientId) }
        logger.info("basket = $basket")
        client.addBasket(basket)
        logger.info("basket = $basket")
        return clientRepository.save(client)
    }

    fun removeBasketFromClient(clientId: Long, basketId: Long) {
        val client = clientRepository.findById(clientId)
            .orElseThrow { ClientNotFoundException(clientId) }

        val basketToRemove = client.baskets.find { it.id == basketId }
            ?: throw BasketNotFoundException("Basket not found for the client")

        client.removeBasket(basketToRemove)
        clientRepository.save(client)
    }
}