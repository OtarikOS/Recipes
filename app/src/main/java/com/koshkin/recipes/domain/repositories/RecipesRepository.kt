package com.koshkin.recipes.domain.repositories

import com.koshkin.recipes.domain.entity.Recipes
import  com.koshkin.recipes.domain.common.Result

interface RecipesRepository {
    suspend fun getRemoteRecipes(tag: String?,ingredient: String): Result<List<Recipes>>

    suspend fun getRemoteRecipeInfo(recipeID:Int): Result<Recipes>
}