package com.koshkin.recipes.data.repositories

import com.koshkin.recipes.domain.entity.RecipesForFragment
import com.koshkin.recipes.domain.common.Result
import com.koshkin.recipes.domain.entity.KeyTrans
import com.koshkin.recipes.domain.entity.Results
import com.koshkin.recipes.domain.entity.Translate
import okhttp3.RequestBody


interface RecipesRemoteDataSource {
    suspend fun getRecipes(from: Int,size: Int,tag:String?,ingredient:String?): Result<List<RecipesForFragment>>

    suspend fun getRecipe(recipeID: Int): Result<Results>

    suspend fun postRecipe(requestBody: RequestBody): Int

    suspend fun getKey(): KeyTrans

    suspend fun translate(authorizationKey:String,requestBody: RequestBody):Translate
}