package com.koshkin.recipes.data

import com.koshkin.recipes.BuildConfig
import retrofit2.http.GET
import retrofit2.http.Query

interface DataRecipesAPI {
    val apiKey: String
        get() = BuildConfig.ApiKey

    @GET("recipes/list?apiKey&from=0&size=20&")
    suspend fun getListRecipes(@Query("tags") tag:String?,@Query("q") ingredient:String?)

    @GET("recipes/get-more-info?apiKey/{id}")
    suspend fun getRecipeInfo()
}