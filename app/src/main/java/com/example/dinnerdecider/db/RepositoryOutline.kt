package com.example.dinnerdecider.db

interface RepositoryOutline {
    fun getCategories()
    fun getList(): List<String>
}