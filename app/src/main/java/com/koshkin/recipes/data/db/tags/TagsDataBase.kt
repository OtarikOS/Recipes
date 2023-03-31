package com.koshkin.recipes.data.db.tags

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [TagsDB::class], version = 1)
abstract class TagsDataBase:RoomDatabase() {
    abstract fun tagsDao():TagsDao

    companion object{
        @Volatile
        private var INSTANCE: TagsDataBase? = null

        fun getTagsDB(context: Context): TagsDataBase{
            val tempInstance = INSTANCE

            if(tempInstance!= null){
                return tempInstance
            }
            synchronized(this){
                val instance = Room.databaseBuilder(
                    context,TagsDataBase::class.java,
                    TagsDataBase::class.simpleName!!
                ).createFromAsset("tags_recipe.db")
                    .allowMainThreadQueries()
                    .build()
                INSTANCE = instance
                return instance
            }

        }
    }
}