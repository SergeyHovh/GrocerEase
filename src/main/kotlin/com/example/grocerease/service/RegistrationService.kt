package com.example.grocerease.service

import com.example.grocerease.exceptions.ClientNotFoundException
import com.example.grocerease.model.Client
import com.example.grocerease.repository.ClientRepository
import org.springframework.stereotype.Service

@Service
class RegistrationService(
    private val clientRepository: ClientRepository
) {
    fun saveClient(client: Client): Client {
        return clientRepository.save(client)
    }

    fun getClientById(id: Long): Client? = clientRepository.findById(id).orElseThrow { ClientNotFoundException(id) }
}