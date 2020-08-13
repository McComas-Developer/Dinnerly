package com.example.dinnerdecider.db

import android.util.Log

class Repository(private val db: AppDatabase): RepositoryOutline {
    var categoryInfo: List<Categories> = listOf()
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

    // Grab Category names only from Database
    override fun getCategories(): ArrayList<String> {
        val temp: ArrayList<String> = arrayListOf()
        for(i in categoryInfo.indices)
            temp.add(categoryInfo[i].category)
        return temp
    }

    // Prepare list of strings for database input
    override fun setListOfCategories(list: Array<String>, list2: Array<String>) {
        categoryInfo = list.map { Categories(it, null, "English") } +
                list2.map { Categories(it, null, "español") }
    }

}