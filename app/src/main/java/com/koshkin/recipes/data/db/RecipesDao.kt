package com.koshkin.recipes.data.db

import androidx.room.*
import com.koshkin.recipes.data.db.recipesfragmentdb.entity.ScreenDb
import com.koshkin.recipes.domain.entity.RecipesForFragment

@Dao
interface RecipesDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(recipes: List<ScreenDb>)

    @Query("DELETE from screen")
    suspend fun  deleteAll()

}