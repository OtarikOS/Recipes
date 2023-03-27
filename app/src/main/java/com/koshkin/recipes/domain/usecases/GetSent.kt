package com.koshkin.recipes.domain.usecases

import com.koshkin.recipes.domain.repositories.RecipesRepository

class GetSent(private val repository: RecipesRepository) {
    suspend operator fun invoke() = repository.getSaveId()
}