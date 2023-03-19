package com.koshkin.recipes.data.repositories

import com.koshkin.recipes.data.db.RecipesDao
import com.koshkin.recipes.data.mappers.ScreenDbMapper
import com.koshkin.recipes.domain.entity.RecipesForFragment
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

class LocalDataSourceImp(
    private val recipesDao: RecipesDao,
    private val dispatcher: CoroutineDispatcher,
    private val mapper: ScreenDbMapper
): LocalDataSource {
    override suspend fun saveAll(recipes: List<RecipesForFragment>) = withContext(dispatcher){
        recipesDao.insertAll(mapper.toScreenDb(recipes))
    }

    override suspend fun deleteAll() = withContext(dispatcher){
        recipesDao.deleteAll()
    }

    override suspend fun getAll(): List<RecipesForFragment> {
        val savedRecipes = recipesDao.getAll()
        return mapper.toForFragment(savedRecipes)
    }
}