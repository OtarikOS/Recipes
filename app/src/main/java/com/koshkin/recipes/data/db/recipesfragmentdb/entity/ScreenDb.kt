package com.koshkin.recipes.data.db.recipesfragmentdb.entity

import androidx.room.Dao
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "screen")
data class ScreenDb(
    @PrimaryKey
    val id: Int?,
    val name: String?,
    val imageUrl:String?
)
