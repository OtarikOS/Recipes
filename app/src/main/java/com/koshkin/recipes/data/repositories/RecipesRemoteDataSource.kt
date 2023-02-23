package com.koshkin.recipes.data.repositories

import com.koshkin.recipes.domain.common.Result
import com.koshkin.recipes.domain.entity.Results


interface RecipesRemoteDataSource {
    suspend fun getRecipes(tag:String?,ingredient:String): Result<List<Recipes>>

    suspend fun getRecipe(recipeID: Int): Result<Recipes>
}