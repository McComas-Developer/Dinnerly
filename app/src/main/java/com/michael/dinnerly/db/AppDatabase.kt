package com.michael.dinnerly.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Categories::class], version = 1)
abstract class AppDatabase : RoomDatabase(){
    abstract fun categoryDao(): CategoryDao

    companion object {
        @Volatile private var instance: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            return instance ?: synchronized(this) {
                instance ?: buildDatabase(context).also { instance = it }
            }!!
        }

        private fun buildDatabase(context: Context): AppDatabase? {
            if (instance == null){
                synchronized(AppDatabase::class){
                    instance = Room.databaseBuilder(context.applicationContext,
                        AppDatabase::class.java, "dinnerdecided.db").build()
                }
            }
            return instance
        }
        fun destroyDatabase(){ instance = null}
    }
}