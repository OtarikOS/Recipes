package com.koshkin.recipes.domain.usecases

import com.koshkin.recipes.domain.repositories.RecipesRepository

class GetRemoteRecipes(private val repository: RecipesRepository) {
    suspend operator fun invoke(from: Int,size: Int,tag: String?,ingredient: String?) = repository.getRemoteRecipes(from,size,tag, ingredient)
}