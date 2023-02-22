package com.koshkin.recipes.domain.usecases

import com.koshkin.recipes.domain.repositories.RecipesRepository

class GetRecipeInfo(private val repository: RecipesRepository) {
    suspend operator fun invoke(recipeID: Int) =repository.getRemoteRecipeInfo(recipeID)
}