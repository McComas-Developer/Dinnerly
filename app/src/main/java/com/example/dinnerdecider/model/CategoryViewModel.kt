package com.example.dinnerdecider.model

import androidx.lifecycle.ViewModel
import com.example.dinnerdecider.util.Connectivity

class CategoryViewModel: ViewModel() {
    private var list = listOf("Fast Food", "Mexican", "Chinese", "Japanese", "Thai", "Southern",
        "Italian", "Buffet", "Steakhouse", "Deli", "Barbecue", "Indian", "Vegetarian", "Sushi",
        "Breakfast", "Seafood", "Cuban").sorted()
    private var categoryList: List<CategoryModel> = list.map { CategoryModel(it) }

    fun getCategories(): List<CategoryModel>{
        return categoryList
    }
    fun resetCategories(){
        categoryList = list.map { CategoryModel(it) }
    }
    // Determine if application is online
    fun isConnected(): Boolean{
        return Connectivity.isOnline
    }
}