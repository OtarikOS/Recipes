package com.koshkin.recipes.data.repositories

import com.koshkin.recipes.domain.common.Result
import com.koshkin.recipes.domain.entity.KeyTrans
import com.koshkin.recipes.domain.entity.Results
import okhttp3.RequestBody
import retrofit2.Response


interface RecipesRemoteDataSource {
    suspend fun getRecipes(from: Int,size: Int,tag:String?,ingredient:String?): Result<List<Results>>

    suspend fun getRecipe(recipeID: Int): Result<Results>

    suspend fun postRecipe(requestBody: RequestBody): Int

    suspend fun getKey(): KeyTrans
}