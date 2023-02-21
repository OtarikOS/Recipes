package com.koshkin.recipes.data.api

import com.koshkin.recipes.BuildConfig
import com.koshkin.recipes.data.entities.Results
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface RecipesAPI {
    val apiKey: String
        get() = BuildConfig.ApiKey

    @GET("recipes/list?apiKey&from=0&size=20&")
    suspend fun getListRecipes(@Query("tags") tag:String?,@Query("q") ingredient:String?): Response<RecipesApiResponse>

    @GET("recipes/get-more-info?apiKey/{id}")
    suspend fun getRecipeInfo():Response<Results>
}