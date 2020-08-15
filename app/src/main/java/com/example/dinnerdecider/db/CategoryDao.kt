package com.example.dinnerdecider.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface CategoryDao{
    @Insert
    fun addCategory(category: Categories)

    @Query("SELECT * FROM Categories WHERE language = :language")
    fun getCategories(language: String): List<Categories>

    @Query("SELECT * FROM Categories")
    fun getCategories(): List<Categories>
}