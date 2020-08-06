package com.example.dinnerdecider.db

import android.util.Log
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class Repository(private val db: CategoryDb): RepositoryOutline {

    private val list = listOf("Fast Food", "Mexican", "Chinese", "Japanese", "Thai", "Southern",
        "Italian", "Buffet", "Steakhouse", "Deli", "Barbecue", "Indian", "Vegetarian", "Sushi",
        "Breakfast", "Seafood", "Cuban").sorted()
    private val categoryInfo = list.map { Categories(it) }
    private val categoryDao = db.categoryDao()

    override fun getCategories() {
        CoroutineScope(Dispatchers.IO).launch{
            for(i in categoryInfo.indices)
                categoryDao.addCategory(categoryInfo[i])
            Log.d("Michael", "Room db: ${db.categoryDao().getCategories()}")
        }
    }

    // Return category list
    override fun getList() = list
}