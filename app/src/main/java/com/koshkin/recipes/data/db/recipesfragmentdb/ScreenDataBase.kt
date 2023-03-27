package com.koshkin.recipes.data.db.recipesfragmentdb

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.koshkin.recipes.data.db.recipesfragmentdb.entity.ScreenDb

@Database(entities = [ScreenDb::class], version = 1)
abstract class ScreenDataBase: RoomDatabase() {
    abstract fun recipesDao(): RecipesDao

    companion object {
        @Volatile
        private var INSTANCE: ScreenDataBase? = null

        fun getDataBase(context: Context): ScreenDataBase{
            val tempInstance = INSTANCE
            if (tempInstance != null){
                return tempInstance
            }
            synchronized(this){
                val instance = Room.databaseBuilder(
                    context,ScreenDataBase::class.java,
                    ScreenDataBase::class.simpleName!!
                ).allowMainThreadQueries()
                    .build()
                INSTANCE = instance
                return instance
            }
        }
    }
}