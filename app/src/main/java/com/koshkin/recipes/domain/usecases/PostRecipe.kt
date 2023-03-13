package com.koshkin.recipes.domain.usecases

import com.koshkin.recipes.domain.repositories.RecipesRepository
import okhttp3.RequestBody

class PostRecipe(private val repository: RecipesRepository) {
    suspend operator fun invoke(requestBody: RequestBody) = repository.postRecipe(requestBody)
}