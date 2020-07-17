package com.example.dinnerdecider

import androidx.lifecycle.ViewModel

class CategoryViewModel: ViewModel() {
    private var list = listOf("Fast Food", "Mexican", "Chinese", "Japanese", "Thai", "Southern",
        "Italian")
    private var categoryList: List<CategoryModel> = list.map { CategoryModel(it) }

    fun getCategories(): List<CategoryModel>{
        return categoryList
    }
    fun resetCategories(){
        categoryList = list.map { CategoryModel(it) }
    }
}