package com.example.grocerease.controllers

import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.get

@ActiveProfiles("test")
@SpringBootTest
@AutoConfigureMockMvc
class MainControllerTest {
    private val basePath: String = "/api/v1/main"

    @Autowired
    lateinit var mockMvc: MockMvc

    @Test
    fun hello() {
        mockMvc.get("${basePath}/hello")
            .andExpect {
                status { is2xxSuccessful() }
            }
    }
}