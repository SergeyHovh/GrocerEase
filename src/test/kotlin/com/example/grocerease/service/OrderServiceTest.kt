package com.example.grocerease.service

import com.example.grocerease.model.Basket
import com.example.grocerease.model.Client
import com.example.grocerease.model.Product
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class OrderServiceTest {
    // create mock objects

    private lateinit var orderService: OrderService
    private lateinit var clientService: ClientService
    private lateinit var basketService: BasketService
    private lateinit var productService: ProductService

    // before each
    // initialize mock objects
    @BeforeEach
    fun setUp() {
        clientService = mockk<ClientService>()
        basketService = mockk<BasketService>()
        productService = mockk<ProductService>()
        orderService = OrderService(clientService, basketService, productService)
    }

    @Test
    fun testAddBasketToClient() {
        val client = mockk<Client>()
        // create sample basket
        val basket = Basket(id = 1L, client = client)

        // mock the behavior of basketService.findBasketById
        every { basketService.findBasketById(basket.id) } returns basket

        // mock the behavior of clientService.addBasketToClient
        every { clientService.addBasketToClient(1L, basket) } returns basket.client!!

        // call the addBasketToClient method
        orderService.addBasketToClient(1L, basket.id)

        // verify that basketService.findBasketById was called once with the provided basket
        verify(exactly = 1) { basketService.findBasketById(basket.id) }

        // verify that clientService.addBasketToClient was called once with the provided client
        verify(exactly = 1) { clientService.addBasketToClient(1L, basket) }
    }

    @Test
    fun testAddProductToBasket() {
        // create sample basket
        val basket = Basket(id = 1L)
        // create sample product
        val product = Product(id = 1L, productName = "Apple", price = 1.0)

        // mock the behavior of basketService.findBasketById
        every { basketService.findBasketById(basket.id) } returns basket

        // mock the behavior of productService.findProduct
        every { productService.findProduct(product.id) } returns product

        every { basketService.updateBasket(basket) } returns basket

        // call the addProductToBasket method
        orderService.addProductToBasket(product.id, basket.id, 1.0)

        // verify that basketService.findBasketById was called once with the provided basket
        verify(exactly = 1) { basketService.findBasketById(basket.id) }

        // verify that productService.findProduct was called once with the provided product
        verify(exactly = 1) { productService.findProduct(product.id) }
    }

    @Test
    fun testGetBasketPrice() {
        // create sample basket
        val basket = Basket(id = 1L)
        // add some products to the basket
        basket.addProduct(Product(id = 1L, productName = "Apple", price = 1.0), 1.0)
        basket.addProduct(Product(id = 2L, productName = "Banana", price = 2.0), 2.0)

        // mock the behavior of basketService.findBasketById
        every { basketService.findBasketById(basket.id) } returns basket

        // call the getBasketPrice method
        orderService.getBasketPrice(basket.id)

        // verify that basketService.findBasketById was called once with the provided basket
        verify(exactly = 1) { basketService.findBasketById(basket.id) }

        // verify that price was calculated correctly
        assert(basket.calculateTotalPrice() == 5.0)
    }

}