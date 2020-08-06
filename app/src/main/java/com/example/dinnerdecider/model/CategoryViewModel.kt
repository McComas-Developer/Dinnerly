package com.example.dinnerdecider.model

import android.content.Context
import androidx.lifecycle.ViewModel
import com.example.dinnerdecider.db.Repository
import com.example.dinnerdecider.db.CategoryDb
import com.example.dinnerdecider.util.Connectivity

class CategoryViewModel: ViewModel() {
    //TODO: Find proper way to grab category list, if language has changed
    //TODO: Create Room database for categories and their descriptions?
    lateinit var db: CategoryDb
    private lateinit var repository: Repository
    private lateinit var categoryList: List<CategoryModel>

    fun getTempList() = repository.getCategories()
    fun getCategories(): List<CategoryModel> = categoryList
    fun setInfo(context: Context){
        db = CategoryDb.getAppDataBase(context)!!
        repository = Repository(db)
    }
    fun setCategories(){ categoryList = repository.getList().map { CategoryModel(it) } }

    // Determine if application is online
    fun isConnected(): Boolean = Connectivity.isOnline
}