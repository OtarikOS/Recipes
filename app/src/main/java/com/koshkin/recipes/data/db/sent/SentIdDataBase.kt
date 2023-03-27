package com.koshkin.recipes.data.db.sent

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [SentIdDb::class], version = 1)
abstract class SentIdDataBase: RoomDatabase(){
    abstract fun sentDao(): SentDao

    companion object{
        @Volatile
        private var INSTANCE: SentIdDataBase? = null

        fun getDB(context: Context): SentIdDataBase{
            val tempInstance = INSTANCE
            if (tempInstance !=null){
                return tempInstance
            }
            synchronized(this){
                val instance = Room.databaseBuilder(
                    context,SentIdDataBase::class.java,
                    SentIdDataBase::class.simpleName!!
                ).allowMainThreadQueries()
                    .build()
                INSTANCE = instance
                return instance
            }
        }
    }
}