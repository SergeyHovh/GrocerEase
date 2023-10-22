package com.example.grocerease.service

import com.example.grocerease.exceptions.ClientAlreadyExistsException
import com.example.grocerease.exceptions.ClientNotFoundException
import com.example.grocerease.model.Client
import com.example.grocerease.repository.ClientRepository
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import java.time.LocalDate
import java.util.*

class ClientServiceTest {

    private lateinit var clientService: ClientService
    private lateinit var clientRepository: ClientRepository

    @BeforeEach
    fun setUp() {
        // Initialize MockK
        MockKAnnotations.init(this)

        // Create a mock instance of ClientRepository
        clientRepository = mockk<ClientRepository>()
        clientService = ClientService(clientRepository)
    }

    @Test
    fun testSaveClient() {
        // Create a sample client
        val client = Client(id = 1L, username = "John Doe", dateOfBirth = LocalDate.now())

        // Mock the behavior of clientRepository.save
        every { clientRepository.save(client) } returns client
        every { clientRepository.findById(client.id) } returns Optional.empty()

        // Call the saveClient method
        clientService.saveClient(client)

        // Verify that clientRepository.save was called once with the provided client
        verify(exactly = 1) { clientRepository.save(client) }
    }

    @Test
    fun testSaveClientAlreadyExists() {
        // Create a sample client
        val client = Client(id = 1L, username = "John Doe", dateOfBirth = LocalDate.now())

        // Mock the behavior of clientRepository.save
        every { clientRepository.save(client) } returns client
        every { clientRepository.findById(client.id) } returns Optional.of(client)

        // Call the saveClient method
        assertThrows<ClientAlreadyExistsException> { clientService.saveClient(client) }
    }

    @Test
    fun testGetClientById() {
        // Create a sample client
        val clientId = 1L
        val client = Client(id = clientId, username = "John Doe", dateOfBirth = LocalDate.now())

        // Mock the behavior of clientRepository.findById
        every { clientRepository.findById(clientId) } returns Optional.of(client)

        // Call the getClientById method
        val retrievedClient = clientService.findClientById(clientId)

        // Verify that clientRepository.findById was called once with the provided ID
        verify(exactly = 1) { clientRepository.findById(clientId) }

        // Verify that the retrieved client matches the expected client
        assert(retrievedClient != null)
        assertEquals(client, retrievedClient)
    }

    @Test
    fun testGetClientByIdNotFound() {
        // Specify a client ID that doesn't exist in the repository
        val nonExistentClientId = 100L

        // Mock the behavior of clientRepository.findById to return an empty Optional
        every { clientRepository.findById(nonExistentClientId) } returns Optional.empty()

        // Use assertThrows to capture and assert the exception
        assertThrows<ClientNotFoundException> {
            clientService.findClientById(nonExistentClientId)
        }

        // Verify that clientRepository.findById was called once with the provided ID
        verify(exactly = 1) { clientRepository.findById(nonExistentClientId) }
    }

    @Test
    fun testClientUpdateHappyCase() {
        val clientId = 1L
        val client = Client(clientId, "abc", LocalDate.now())

        every { clientRepository.findById(clientId) } returns Optional.of(client)
        every { clientRepository.save(client) } returns client

        val updatedClient = clientService.updateClient(client)

        verify(exactly = 1) {
            clientRepository.findById(clientId)
            clientRepository.save(client)
        }

        assertEquals(client, updatedClient)
    }

    @Test
    fun testClientUpdateBadCase() {
        val clientId = 1L
        val client = Client(clientId, "abc", LocalDate.now())

        every { clientRepository.findById(clientId) } throws ClientNotFoundException(clientId)
        every { clientRepository.save(client) } returns client

        assertThrows<ClientNotFoundException> {
            clientService.updateClient(client)
        }

        verify(exactly = 1) {
            clientRepository.findById(clientId)
        }

        verify(exactly = 0) {
            clientRepository.save(client)
        }
    }
}