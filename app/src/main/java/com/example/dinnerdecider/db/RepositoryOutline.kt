package com.example.dinnerdecider.db


interface RepositoryOutline {
    suspend fun setDatabaseCategories()
    fun setListOfCategories(list: Array<String>, list2: Array<String>)
    suspend fun getCategoryList(language: String): List<Categories>
    fun getCategories(): ArrayList<String>
}