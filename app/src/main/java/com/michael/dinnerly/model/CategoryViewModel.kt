package com.michael.dinnerly.model

import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.michael.dinnerly.db.AppDatabase
import com.michael.dinnerly.db.Repository
import com.michael.dinnerly.util.Connectivity
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

    fun setCategoryList(catEng: Array<String>, desEng: Array<String>, catSpa: Array<String>, desSpa: Array<String>){
        CoroutineScope(Dispatchers.IO).launch{
            if(repository.getCategoryList(determineLanguage()).isEmpty()) {
                Log.d("Michael", "Category list is empty")
                repository.setListOfCategories(catEng, desEng, catSpa, desSpa)
                withContext(Dispatchers.Default) { repository.setDatabaseCategories() }
            }
            repository.getCategoryList(determineLanguage())
            val temp = repository.getCategories().zip(repository.getDescriptions()).map {
                CategoryModel(it.first, it.second) }
            categoryList.postValue(temp)
        }
    }
    // Get language of device
    private fun determineLanguage() = Locale.getDefault().displayLanguage.toString()
    
    // Determine if application is online
    fun isConnected(): Boolean = Connectivity.isOnline
}