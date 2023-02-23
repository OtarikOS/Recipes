package com.koshkin.recipes.domain.usecases

import com.koshkin.recipes.domain.repositories.RecipesRepository

class GetRemoteRecipes(private val repository: RecipesRepository) {
    suspend operator fun invoke(tag: String?,ingredient: String?) = repository.getRemoteRecipes(tag, ingredient)
}