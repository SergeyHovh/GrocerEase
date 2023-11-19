package com.example.grocerease.model.edmam

data class EdamamRecipe(
    val uri: String,
    val label: String,
    val image: String,
    val source: String,
    val url: String,
    val shareAs: String,
    val yield: Double,
    val dietLabels: List<String>,
    val healthLabels: List<String>,
    val cautions: List<String>,
    val ingredientLines: List<String>,
    val ingredients: List<Ingredient>,
    val calories: Double,
    val totalWeight: Double,
    val totalTime: Double,
    val cuisineType: List<String>,
    val mealType: List<String>,
    val dishType: List<String>
)

data class Ingredient(
    val text: String,
    val quantity: Double,
    val measure: String,
    val food: String,
    val weight: Double,
    val foodId: String
)