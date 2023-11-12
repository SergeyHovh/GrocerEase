package com.example.grocerease.repository

import com.example.grocerease.model.Basket
import org.springframework.data.jpa.repository.JpaRepository

interface BasketRepository : JpaRepository<Basket, Long>