package com.koshkin.recipes.data.db.tags

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query

@Dao
 interface TagsDao {
    @Query("SELECT * FROM tags_recipe")
    fun getTags(): LiveData<List<TagsDB>>
}
