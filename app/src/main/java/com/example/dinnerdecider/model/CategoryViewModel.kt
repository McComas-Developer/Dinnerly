package com.example.dinnerdecider.model

import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.dinnerdecider.db.AppDatabase
import com.example.dinnerdecider.db.Repository
import com.example.dinnerdecider.util.Connectivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.*

class CategoryViewModel: ViewModel() {
    //TODO: Find way to store category descriptions alongside their designated categories
    private lateinit var db: AppDatabase
    private lateinit var repository: Repository
    private var categoryList = MutableLiveData<List<CategoryModel>>()

    fun getCategories(): MutableLiveData<List<CategoryModel>> = categoryList
    fun setInfo(context: Context){
        db = AppDatabase.getInstance(context)
        repository = Repository(db)
    }

    fun setCategoryList(list: Array<String>, list2: Array<String>){
        CoroutineScope(Dispatchers.IO).launch{
            if(repository.getCategoryList(determineLanguage()).isEmpty()) {
                Log.d("Michael", "Category list is empty")
                repository.setListOfCategories(list, list2)
                withContext(Dispatchers.Default) { repository.setDatabaseCategories() }
            }
            repository.getCategoryList(determineLanguage())
            val temp = repository.getCategories().map { CategoryModel(it) }
            categoryList.postValue(temp)
        }
    }
    // Get language of device
    private fun determineLanguage() = Locale.getDefault().displayLanguage.toString()
    
    // Determine if application is online
    fun isConnected(): Boolean = Connectivity.isOnline
}