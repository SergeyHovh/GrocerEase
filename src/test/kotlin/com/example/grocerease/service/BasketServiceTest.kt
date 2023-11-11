package com.example.grocerease.service

import com.example.grocerease.exceptions.BasketAlreadyExistsException
import com.example.grocerease.model.Basket
import com.example.grocerease.repository.BasketRepository
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import java.util.*

// test class for BasketService
class BasketServiceTest {
    private lateinit var basketService: BasketService
    private lateinit var basketRepository: BasketRepository

    @BeforeEach
    fun setUp() {
        // Initialize MockK
        MockKAnnotations.init(this)

        // Create a mock instance of ClientRepository
        basketRepository = mockk<BasketRepository>()
        basketService = BasketService(basketRepository)
    }

    @Test
    fun testFindBasketById() {
        // Create a sample basket
        val basket = Basket(id = 1L)

        // Mock the behavior of basketRepository.findById
        every { basketRepository.findById(basket.id) } returns Optional.of(basket)

        // Call the findBasketById method
        basketService.findBasketById(basket.id)

        // Verify that basketRepository.findById was called once with the provided basket
        verify(exactly = 1) { basketRepository.findById(basket.id) }
    }

    @Test
    fun testSaveBasket() {
        // Create a sample basket
        val basket = Basket(id = 1L)

        // Mock the behavior of basketRepository.save
        every { basketRepository.save(basket) } returns basket
        every { basketRepository.findById(basket.id) } returns Optional.empty()

        // Call the saveBasket method
        basketService.saveBasket(basket)

        // Verify that basketRepository.save was called once with the provided basket
        verify(exactly = 1) { basketRepository.save(basket) }
    }

    @Test
    fun testUpdateBasket() {
        // Create a sample basket
        val basket = Basket(id = 1L)

        // Mock the behavior of basketRepository.save
        every { basketRepository.save(basket) } returns basket
        every { basketRepository.findById(basket.id) } returns Optional.of(basket)

        // Call the updateBasket method
        basketService.updateBasket(basket)

        // Verify that basketRepository.save was called once with the provided basket
        verify(exactly = 1) { basketRepository.save(basket) }
    }

    @Test
    fun testUpdateBasketNotFound() {
        // Create a sample basket
        val basket = Basket(id = 1L)

        // Mock the behavior of basketRepository.save
        every { basketRepository.save(basket) } returns basket
        every { basketRepository.findById(basket.id) } returns Optional.of(basket)

        // Call the updateBasket method
        basketService.updateBasket(basket)

        // Verify that basketRepository.save was called once with the provided basket
        verify(exactly = 1) { basketRepository.save(basket) }
    }

    @Test
    fun testSaveBasketAlreadyExists() {
        // Create a sample basket
        val basket = Basket(id = 1L)

        // Mock the behavior of basketRepository.save
        every { basketRepository.save(basket) } returns basket
        every { basketRepository.findById(basket.id) } returns Optional.of(basket)

        // Call the saveBasket method
        assertThrows<BasketAlreadyExistsException> { basketService.saveBasket(basket) }
    }
}