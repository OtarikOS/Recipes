package com.koshkin.recipes.domain.usecases

import com.koshkin.recipes.domain.entity.RecipesForFragment
import com.koshkin.recipes.domain.repositories.RecipesRepository

class SaveAllRecipes(private val repository: RecipesRepository) {
    suspend operator fun invoke(recipes:List<RecipesForFragment>) = repository.saveAll(recipes)
}