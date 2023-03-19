package com.koshkin.recipes.domain.usecases

import com.koshkin.recipes.domain.repositories.RecipesRepository

class GetSavedRecipes(private val repository: RecipesRepository) {
    suspend operator fun invoke() = repository.getAll()
}