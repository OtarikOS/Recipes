package com.koshkin.recipes.di

import android.content.Context
import com.koshkin.recipes.BuildConfig
import com.koshkin.recipes.data.api.NetworkModule
import com.koshkin.recipes.data.db.recipesfragmentdb.ScreenDataBase
import com.koshkin.recipes.data.mappers.KeyMapper
import com.koshkin.recipes.data.mappers.RecipesApiResponseMapper
import com.koshkin.recipes.data.mappers.ScreenDbMapper
import com.koshkin.recipes.data.repositories.LocalDataSource
import com.koshkin.recipes.data.repositories.LocalDataSourceImp
import com.koshkin.recipes.data.repositories.RecipesRemoteDataSourceImp
import com.koshkin.recipes.data.repositories.RecipesRepositoryImpl
import kotlinx.coroutines.Dispatchers

object ServiceLocator {
    private var screenDataBase: ScreenDataBase? = null
    private val networkModule by lazy {
        NetworkModule()
    }

    private val screenDbMapper by lazy {
        ScreenDbMapper()
    }

    @Volatile
    var recipesRepository: RecipesRepositoryImpl? = null

    fun provideRecipesRepository(context: Context) : RecipesRepositoryImpl{
        synchronized(this){
            return recipesRepository ?: createRecipesRepository(context)
        }
    }

    private fun createRecipesRepository(context: Context): RecipesRepositoryImpl {
        val newRepo =
            RecipesRepositoryImpl(
                RecipesRemoteDataSourceImp(
                networkModule.createRecipesApi(BuildConfig.TASTY_APIS_ENDPOINT),
                RecipesApiResponseMapper(),
                   KeyMapper()
                ),
                createLocalDataSource(context)
            )
        recipesRepository = newRepo
        return  newRepo
    }

    private fun createLocalDataSource(context: Context): LocalDataSource {
        val database = screenDataBase ?: createDataBase(context)
        return LocalDataSourceImp(
            database.recipesDao(),
            Dispatchers.IO,
            screenDbMapper
        )
    }

    private fun createDataBase(context: Context): ScreenDataBase {
        val result = ScreenDataBase.getDataBase(context)
        screenDataBase = result
        return result
    }
}