package com.example.grocerease.controllers

import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.get

@SpringBootTest
@AutoConfigureMockMvc
class MainControllerTest {
    private val basePath: String = "/api/v1/main"

    @Autowired
    lateinit var mockMvc: MockMvc

    @Test
    fun hello() {
        mockMvc.get("${basePath}/hello")
            .andDo { print() }
            .andExpect {
                status { is2xxSuccessful() }
            }
    }

    @Test
    fun failing() {
        mockMvc.get("$basePath/hello")
            .andExpect {
                status { isOk() }
                jsonPath("$") { value("Hello    world ") }
            }
    }
}