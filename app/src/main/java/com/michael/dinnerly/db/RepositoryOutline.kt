package com.michael.dinnerly.db


interface RepositoryOutline {
    suspend fun setDatabaseCategories()
    fun setListOfCategories(catEng: Array<String>, desEng: Array<String>, catSpa: Array<String>, desSpa: Array<String>)
    suspend fun getCategoryList(language: String): List<Categories>
    fun getCategories(): ArrayList<String>
    fun getDescriptions(): ArrayList<String>
}