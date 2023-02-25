package com.koshkin.recipes.data.repositories

import com.koshkin.recipes.domain.common.Result
import com.koshkin.recipes.domain.entity.Results


interface RecipesRemoteDataSource {
    suspend fun getRecipes(from: Int,size: Int,tag:String?,ingredient:String?): Result<List<Results>>

    suspend fun getRecipe(recipeID: Int): Result<Results>
}