package com.example.grocerease.controllers

import com.example.grocerease.model.Client
import com.fasterxml.jackson.databind.ObjectMapper
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.post
import java.time.LocalDate

@SpringBootTest
@AutoConfigureMockMvc
class RegistrationControllerTest @Autowired constructor(
    private val mockMvc: MockMvc,
    private val objectMapper: ObjectMapper
) {
    private val basePath: String = "/api/v1/registration"

    @Test
    fun testClientCreation() {
        val mockClient = Client(1L, "abc", LocalDate.now())
        mockMvc.post("$basePath/create") {
            contentType = MediaType.APPLICATION_JSON
            content = objectMapper.writeValueAsString(mockClient)!!
        }.andExpect {
            status { isCreated() }
        }
    }
}