package com.example.grocerease.service

import com.example.grocerease.exceptions.ProductNotFoundException
import com.example.grocerease.model.Product
import com.example.grocerease.repository.ProductRepository
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service

@Service
class ProductService(
    private val productRepository: ProductRepository
) {
    private val logger: Logger = LoggerFactory.getLogger(ProductService::class.java)

    fun findProduct(productId: Long): Product =
        productRepository.findById(productId).orElseThrow { ProductNotFoundException(productId) }


}