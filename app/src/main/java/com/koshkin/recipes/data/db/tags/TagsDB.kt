package com.koshkin.recipes.data.db.tags

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tags_recipe")
data class TagsDB(
    val id:Int,
    val type:String,
    @PrimaryKey
    val name:String,
    val dysplay_text:String
)
