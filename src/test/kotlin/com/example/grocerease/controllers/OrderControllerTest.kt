package com.example.grocerease.controllers

import com.example.grocerease.GrocerEaseApplication
import com.fasterxml.jackson.databind.ObjectMapper
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.get
import org.springframework.test.web.servlet.post

@SpringBootTest(classes = [GrocerEaseApplication::class])
@ActiveProfiles("test")
@AutoConfigureMockMvc
class OrderControllerTest @Autowired constructor(
    private val mockMvc: MockMvc,
    private val objectMapper: ObjectMapper
) {

    private val basePath: String = "/api/v1/orders"

    // test creating an order by passing in a client id and a basket id
    @Test
    fun testOrderCreation() {
        mockMvc.post("$basePath/create") {
            contentType = MediaType.APPLICATION_JSON
            param("clientId", "1")
            param("basketId", "1")
        }.andExpect {
            status { isCreated() }
            content {
                contentType(MediaType.APPLICATION_JSON)
            }
        }
    }

    // test adding a product to a basket by passing in a product id, basket id, and amount
    @Test
    fun testAddProductToBasket() {
        mockMvc.post("$basePath/add-product") {
            contentType = MediaType.APPLICATION_JSON
            param("productId", "1")
            param("basketId", "1")
            param("amount", "1")
        }.andExpect {
            status { isCreated() }
            content {
                contentType(MediaType.APPLICATION_JSON)
            }
        }
    }

    // test getting the price of a basket by passing in a basket id
    @Test
    fun testGetPrice() {
        mockMvc.get("$basePath/get-price") {
            contentType = MediaType.APPLICATION_JSON
            param("basketId", "1")
        }.andExpect {
            status { isOk() }
            content {
                contentType(MediaType.APPLICATION_JSON)
            }
        }
    }


    fun asJson(any: Any): String = objectMapper.writeValueAsString(any)
}