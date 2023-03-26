package com.koshkin.recipes.data.repositories

import android.util.Log
import com.koshkin.recipes.data.api.RecipesAPI
import com.koshkin.recipes.data.mappers.KeyMapper
import com.koshkin.recipes.data.mappers.RecipesApiResponseMapper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import com.koshkin.recipes.domain.common.Result
import com.koshkin.recipes.domain.entity.*
import okhttp3.RequestBody

class RecipesRemoteDataSourceImp(
    private val service: RecipesAPI,
    private val mapper: RecipesApiResponseMapper,
    private val mapperKey:KeyMapper
): RecipesRemoteDataSource {
    override suspend fun getRecipes(
        from: Int,
        size: Int,
        tag: String?,
        ingredient: String?
    ): Result<List<RecipesForFragment>> =
        withContext(Dispatchers.IO) {
            try {
                val response = service.getListRecipes(from, size, tag, ingredient)
                if (response.isSuccessful) {
                    return@withContext Result.Success(mapper.toScreenDb(response.body()!!))
                } else {
                    return@withContext Result.Error(Exception(response.message()))
                }
            } catch (e: Exception) {
                return@withContext Result.Error(e)
            }
        }

    override suspend fun getRecipe(recipeID: Int): Result<Results> =
        withContext(Dispatchers.IO) {
            try {
                val response = service.getRecipeInfo(recipeID)
                if (response.isSuccessful) {
                    return@withContext Result.Success(mapper.toResults(response.body()!!))
                } else {
                    return@withContext Result.Error(Exception(response.message()))
                }
            } catch (e: Exception) {
                return@withContext Result.Error(e)
            }
        }

    override suspend fun postRecipe(requestBody: RequestBody): Int =
        withContext(Dispatchers.IO) {
       //     try {
            val result:Int
                val response = service.postRecipe(requestBody)
                if (response.isSuccessful) {
                    Log.i("res_RRDS",response.code().toString())
                    result = response.code()
      //              return@withContext Result.Success(1)

                    // Convert raw JSON to pretty JSON using GSON library
//                    val gson = GsonBuilder().setPrettyPrinting().create()
//                    val prettyJson = gson.toJson(
//                        JsonParser.parseString(
//                            response.body()?.string() // About this thread blocking annotation : https://github.com/square/retrofit/issues/3255
//                        )
//                    )
////
//                    Log.d("Pretty Printed JSON :", prettyJson)

                } else {
                    Log.i("res_RRDS_else",response.errorBody().toString())
                   // return@withContext Result.Error(Exception(response.message()))
                    Log.i("res_RRDS_else",response.message())

                    result =response.code()
                }
            return@withContext result
//            } catch (e: Exception) {
//                Log.i("res_RRDS_catch",e.message.toString())
////             //   return@withContext Result.Error(e)
          // }
        }

    override suspend fun getKey(): Result<KeyT> =

        withContext(Dispatchers.IO) {
            try {
                val response = service.getKey()
                if (response.isSuccessful) {
                    return@withContext Result.Success(mapperKey.toKeyT(response.body()!!))
                } else {
                    Log.i("RRDS_getKei_error",Result.Error(Exception(response.message())).toString())
                    return@withContext Result.Error(Exception(response.message()))
                }
            } catch (e: Exception) {
                Log.i("RRDS_getKei_catch",e.message.toString())
                return@withContext Result.Error(e)
            }
        }
//        withContext(Dispatchers.IO) {
//            //     try {
//            val result:KeyTrans?
//            val response = service.getKey()
//            if (response.isSuccessful) {
//                Log.i("res_RRDS_key",response.body().toString())
//                result = response.body()
//                Log.i("res_RRDS_key2",result?.key!!)
                //              return@withContext Result.Success(1)


//            } else {
//                Log.i("res_RRDS_else_key",response.errorBody().toString())
//                // return@withContext Result.Error(Exception(response.message()))
//
//                result =response.body()
//            }

            //return@withContext result!!
      //  }

    override suspend fun translate(
        authorizationKey: String,
        requestBody: RequestBody
    ): Translate =
        withContext(Dispatchers.IO) {
            val result: Translate?
            val response = service.translate(authorizationKey,requestBody)
            if (response.isSuccessful) {
                Log.i("res_RRDS_key", response.body().toString())
                result = response.body()
                Log.i("res_RRDS_key2", result?.translations.toString())

            } else {
                       //Костыль. Вместо Result<out R>
                result =Translate()
                Log.i("res_RRDS_else_key", result.translations.toString())
                // return@withContext Result.Error(Exception(response.message()))


            }
            return@withContext result!!
        }
}