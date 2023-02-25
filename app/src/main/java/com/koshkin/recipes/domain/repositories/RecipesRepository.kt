package com.koshkin.recipes.domain.repositories

import com.koshkin.recipes.domain.entity.Recipes
import  com.koshkin.recipes.domain.common.Result
import com.koshkin.recipes.domain.entity.Results

interface RecipesRepository {
    suspend fun getRemoteRecipes(from: Int,tag: String?,ingredient: String?): Result<List<Results>>

    suspend fun getRemoteRecipeInfo(recipeID:Int): Result<Results>
}