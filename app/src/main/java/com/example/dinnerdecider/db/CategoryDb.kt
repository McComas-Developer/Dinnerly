package com.example.dinnerdecider.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Categories::class], version = 1)
abstract class CategoryDb : RoomDatabase(){
    abstract fun categoryDao(): CategoryDao

    companion object {
        var INSTANCE: CategoryDb? = null

        fun getAppDataBase(context: Context?): CategoryDb? {
            if (INSTANCE == null){
                synchronized(CategoryDb::class){
                    INSTANCE = Room.databaseBuilder(context!!.applicationContext,
                        CategoryDb::class.java, "myDB").build()
                }
            }
            return INSTANCE
        }

        fun destroyDataBase(){ INSTANCE = null }
    }
}