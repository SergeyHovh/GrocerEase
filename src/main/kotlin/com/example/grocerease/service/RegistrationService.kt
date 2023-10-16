package com.example.grocerease.service

import com.example.grocerease.exceptions.ClientAlreadyExistsException
import com.example.grocerease.exceptions.ClientNotFoundException
import com.example.grocerease.model.Client
import com.example.grocerease.repository.ClientRepository
import org.springframework.stereotype.Service

@Service
class RegistrationService(
    private val clientRepository: ClientRepository
) {
    fun saveClient(client: Client): Client {
        clientRepository.findById(client.id).ifPresent { throw ClientAlreadyExistsException(client.id) }
        return clientRepository.save(client)
    }

    fun getClientById(id: Long): Client? = clientRepository.findById(id).orElseThrow { ClientNotFoundException(id) }

    fun updateClient(client: Client): Client {
        clientRepository.findById(client.id).orElseThrow { ClientNotFoundException(client.id) }
        return clientRepository.save(client)
    }
}