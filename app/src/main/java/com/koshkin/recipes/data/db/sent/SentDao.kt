package com.koshkin.recipes.data.db.sent

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface SentDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(id:SentIdDb)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun update(list: List<SentIdDb>)

//    @Query("DELETE from sentId")
//    suspend fun delete(list: List<SentIdDb>)

    @Query("SELECT * FROM sentID")
    fun getAllId(): Flow<List<SentIdDb>>


}