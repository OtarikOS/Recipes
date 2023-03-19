package com.koshkin.recipes.domain.usecases

import com.koshkin.recipes.domain.repositories.RecipesRepository

class DeleteAll(private  val repository: RecipesRepository) {
    suspend operator fun invoke() = repository.deleteAll()
}