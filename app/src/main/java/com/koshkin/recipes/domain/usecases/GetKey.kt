package com.koshkin.recipes.domain.usecases

import com.koshkin.recipes.domain.repositories.RecipesRepository

class GetKey(private val repository: RecipesRepository) {
    suspend operator fun invoke() = repository.getKey()
}