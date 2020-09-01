package com.example.dinnerdecider.db

import android.util.Log

class Repository(private val db: AppDatabase): RepositoryOutline {
    private var categoryInfo: List<Categories> = listOf()
    private val categoryDao = db.categoryDao()

    // Set Categories in Database
    override suspend fun setDatabaseCategories() {
        for(i in categoryInfo.indices)
            categoryDao.addCategory(categoryInfo[i])
        Log.d("Michael", "Room db: ${db.categoryDao().getCategories()}")
    }
    // Get categories based on language
    override suspend fun getCategoryList(language: String): List<Categories>{
        categoryInfo = db.categoryDao().getCategories(language)
        Log.d("Michael", "Category List${db.categoryDao().getCategories(language)}")
        return categoryInfo
    }

    // Grab category names from Database
    override fun getCategories(): ArrayList<String> {
        val temp: ArrayList<String> = arrayListOf()
        for(i in categoryInfo.indices)
            temp.add(categoryInfo[i].category)
        return temp
    }

    // Grab descriptions from Database
    override fun getDescriptions(): ArrayList<String> {
        val temp: ArrayList<String> = arrayListOf()
        for(i in categoryInfo.indices)
            temp.add(categoryInfo[i].description)
        return temp
    }

    // Prepare list of strings for database input
    override fun setListOfCategories(catEng: Array<String>, desEng: Array<String>, catSpa: Array<String>, desSpa: Array<String>) {
        val listEng = catEng.zip(desEng).map { Categories(it.first, it.second, "English") }
        val listSpa = catSpa.zip(desSpa).map { Categories(it.first, it.second, "español") }
        categoryInfo = listEng + listSpa
    }
}