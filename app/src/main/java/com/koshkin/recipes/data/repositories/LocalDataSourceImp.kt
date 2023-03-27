package com.koshkin.recipes.data.repositories

import com.koshkin.recipes.data.db.recipesfragmentdb.RecipesDao
import com.koshkin.recipes.data.db.sent.SentDao
import com.koshkin.recipes.data.mappers.ScreenDbMapper
import com.koshkin.recipes.data.mappers.sent.SentIdMapper
import com.koshkin.recipes.domain.entity.RecipesForFragment
import com.koshkin.recipes.domain.entity.sent.SentIdDomain
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext

class LocalDataSourceImp(
    private val recipesDao: RecipesDao,
    private val dispatcher: CoroutineDispatcher,
    private val mapper: ScreenDbMapper,
    private val sentDao: SentDao,
    private val sentIdMapper: SentIdMapper
) : LocalDataSource {
    override suspend fun saveAll(recipes: List<RecipesForFragment>) = withContext(dispatcher) {
        recipesDao.insertAll(mapper.toScreenDb(recipes))
    }

    override suspend fun deleteAll() = withContext(dispatcher) {
        recipesDao.deleteAll()
    }

    override suspend fun getAll(): List<RecipesForFragment> {
        val savedRecipes = recipesDao.getAll()
        return mapper.toForFragment(savedRecipes)
    }

    //SentDb
    override suspend fun insertId(id: SentIdDomain) = withContext(dispatcher) {
        sentDao.insert(sentIdMapper.toSentDb(id))
    }

    override suspend fun getSaveId(): Flow<List<SentIdDomain>> {
        val savedSendId = sentDao.getAllId()
        return savedSendId.map { list ->
            list.map { element ->
                sentIdMapper.toSentDomain(element)
            }
        }
    }
}