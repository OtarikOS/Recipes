package com.koshkin.recipes.domain.repositories

import com.koshkin.recipes.domain.entity.Recipes
import  com.koshkin.recipes.domain.common.Result
import com.koshkin.recipes.domain.entity.Results
import okhttp3.RequestBody

interface RecipesRepository {
    suspend fun getRemoteRecipes(from: Int,size: Int,tag: String?,ingredient: String?): Result<List<Results>>

    suspend fun getRemoteRecipeInfo(recipeID:Int): Result<Results>

    suspend fun postRecipe(requestBody: RequestBody): Int
}