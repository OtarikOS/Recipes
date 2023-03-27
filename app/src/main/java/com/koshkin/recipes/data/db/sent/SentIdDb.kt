package com.koshkin.recipes.data.db.sent

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "sentId")
data class SentIdDb(
    @PrimaryKey
    val id:Int
)
