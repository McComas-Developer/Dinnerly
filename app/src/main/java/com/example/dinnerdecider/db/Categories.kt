package com.example.dinnerdecider.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Categories(
    val category: String,
    val description: String? = null,
    val language: String? = null,
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null
)