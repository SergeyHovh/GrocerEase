package com.example.grocerease.controllers

import com.example.grocerease.model.Client
import com.fasterxml.jackson.databind.ObjectMapper
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.get
import org.springframework.test.web.servlet.patch
import org.springframework.test.web.servlet.post
import java.time.LocalDate

@ActiveProfiles("test")
@SpringBootTest
@AutoConfigureMockMvc
class RegistrationControllerTest @Autowired constructor(
    private val mockMvc: MockMvc,
    private val objectMapper: ObjectMapper
) {
    private val basePath: String = "/api/v1/registration"

    @Test
    fun testClientCreation() {
        val mockClient = Client(1L, "abcde", LocalDate.now())
        mockMvc.post("$basePath/create") {
            contentType = MediaType.APPLICATION_JSON
            content = asJson(mockClient)
        }.andExpect {
            status { isCreated() }
            content {
                contentType(MediaType.APPLICATION_JSON)
                json(asJson(mockClient))
            }
        }
    }

    @Test
    fun testClientGetHappyPath() {
        val clientId = 1L
        mockMvc.get("$basePath/$clientId").andExpect { status { isOk() } }
    }

    @Test
    fun testClientGetNotFoundPath() {
        val notFoundClientId = 200L
        mockMvc.get("$basePath/$notFoundClientId").andExpect { status { isNotFound() } }
    }

    @Test
    fun testClientPathHappyPath() {
        val clientId = 1L
        val mockClient = Client(clientId, "updated", LocalDate.now())
        mockMvc.patch(basePath) {
            contentType = MediaType.APPLICATION_JSON
            content = asJson(mockClient)
        }.andExpect {
            status { isAccepted() }
            content {
                contentType(MediaType.APPLICATION_JSON)
                json(asJson(mockClient))
            }
        }
    }

    @Test
    fun testClientPathNotFoundPath() {
        val clientId = 200L
        val mockClient = Client(clientId, "asd", LocalDate.now())
        mockMvc.patch(basePath) {
            contentType = MediaType.APPLICATION_JSON
            content = asJson(mockClient)
        }.andExpect { status { isNotFound() } }
    }

    private fun asJson(client: Client): String = objectMapper.writeValueAsString(client)
}