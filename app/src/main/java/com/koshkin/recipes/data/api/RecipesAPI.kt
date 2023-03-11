package com.koshkin.recipes.data.api

import com.koshkin.recipes.BuildConfig
import okhttp3.RequestBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface RecipesAPI {
    val apiKey: String
        get() = BuildConfig.ApiKey

    @GET("recipes/list?${BuildConfig.ApiKey2}&tag=under_30_minutes")
    suspend fun getListRecipes(@Query("from") from: Int,
                               @Query("size") size: Int,
                               @Query("tags") tag:String?,
                               @Query("q") ingredient:String?): Response<RecipesApiResponse>

    @GET("recipes/get-more-info?${BuildConfig.ApiKey2}")
    suspend fun getRecipeInfo(@Query("id") recipeID:Int):Response<ResultsApi>

    @POST("${BuildConfig.POST_END_POINT}dbconnect.php")
    suspend fun postRecipe(@Body requestBody: RequestBody):Response<RequestBody>
}