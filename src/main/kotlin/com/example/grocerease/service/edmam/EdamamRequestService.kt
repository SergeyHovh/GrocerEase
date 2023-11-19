package com.example.grocerease.service.edmam

import com.example.grocerease.model.edmam.EdamamRecipe
import com.example.grocerease.model.edmam.Ingredient
import com.fasterxml.jackson.databind.JsonNode
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.web.client.RestTemplateBuilder
import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate

@Service
class EdamamRequestService(
    @Value("\${edamam.api.id}") private val apiId: String,
    @Value("\${edamam.api.key}") private val apiKey: String,
    restTemplateBuilder: RestTemplateBuilder
) {
    private val restTemplate: RestTemplate = restTemplateBuilder.build()

    fun getRecipesForName(name: String): MutableList<EdamamRecipe> {
        return requestData(mapOf("q" to name))
    }

    fun getRecipesByDiet(diet: String): MutableList<EdamamRecipe> {
        return requestData(mapOf("diet" to diet))
    }

    fun requestData(params: Map<String, String>): MutableList<EdamamRecipe> {
        val query = params.map { "${it.key}=${it.value}" }.joinToString("&")
        val url = "https://api.edamam.com/api/recipes/v2?type=public&app_id=$apiId&app_key=$apiKey&$query"
        return restTemplate.getForObject(url, JsonNode::class.java)?.let { mapResponseToRecipes(it) }!!
    }

    private fun mapResponseToRecipes(response: JsonNode): MutableList<EdamamRecipe> {
        val hits = response.get("hits")
        val recipes = mutableListOf<EdamamRecipe>()
        hits?.forEach {
            recipes.add(mapToEdmamRecipe(it))
        }
        return recipes
    }

    private fun mapToEdmamRecipe(jsonNode: JsonNode): EdamamRecipe {
        val recipe = jsonNode.get("recipe")
        return EdamamRecipe(
            uri = recipe.get("uri").asText(),
            label = recipe.get("label").asText(),
            image = recipe.get("image").asText(),
            source = recipe.get("source").asText(),
            url = recipe.get("url").asText(),
            shareAs = recipe.get("shareAs").asText(),
            yield = recipe.get("yield").asDouble(),
            dietLabels = recipe.get("dietLabels").map { it.asText() },
            healthLabels = recipe.get("healthLabels").map { it.asText() },
            cautions = recipe.get("cautions").map { it.asText() },
            ingredientLines = recipe.get("ingredientLines").map { it.asText() },
            ingredients = recipe.get("ingredients").map { ingredient ->
                Ingredient(
                    text = ingredient.get("text").asText(),
                    quantity = ingredient.get("quantity").asDouble(),
                    measure = ingredient.get("measure").asText(),
                    food = ingredient.get("food").asText(),
                    weight = ingredient.get("weight").asDouble(),
                    foodId = ingredient.get("foodId").asText()
                )
            },
            calories = recipe.get("calories").asDouble(),
            totalWeight = recipe.get("totalWeight").asDouble(),
            totalTime = recipe.get("totalTime").asDouble(),
            cuisineType = recipe.get("cuisineType").map { it.asText() },
            mealType = recipe.get("mealType").map { it.asText() },
            dishType = recipe.get("dishType").map { it.asText() }
        )
    }
}