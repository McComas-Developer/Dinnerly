package com.example.dinnerdecider

import android.util.Log
import com.example.dinnerdecider.db.Categories
import com.example.dinnerdecider.db.CategoryDb
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class Repository(private val db: CategoryDb) {

    private val list = listOf("Fast Food", "Mexican", "Chinese", "Japanese", "Thai", "Southern",
        "Italian", "Buffet", "Steakhouse", "Deli", "Barbecue", "Indian", "Vegetarian", "Sushi",
        "Breakfast", "Seafood", "Cuban").sorted()
    private val categoryInfo = list.map { Categories(it) }
    private val categoryDao = db.categoryDao()

    fun getCategories(){
        CoroutineScope(Dispatchers.IO).launch{
            for(i in categoryInfo.indices)
                categoryDao.addCategory(categoryInfo[i])
            Log.d("Michael", "Room db: ${db.categoryDao().getCategories()}")
        }
    }
    // Return category list
    fun getList() = list
}