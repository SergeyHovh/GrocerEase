package com.example.grocerease.controllers

import com.example.grocerease.model.edmam.EdamamRecipe
import com.example.grocerease.service.edmam.EdamamRequestService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/edamam")
class EdamamRequestController(
    private val edamamRequestService: EdamamRequestService
) {

    @GetMapping("/recipe")
    fun recipe(
        @RequestParam("name") name: String
    ): MutableList<EdamamRecipe>? {
        return edamamRequestService.getRecipesForName(name)
    }

    @GetMapping("/diet")
    fun diet(
        @RequestParam("diet") diet: String
    ): MutableList<EdamamRecipe>? {
        return edamamRequestService.getRecipesByDiet(diet)
    }
}