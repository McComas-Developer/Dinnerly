package com.michael.dinnerly.model

import androidx.lifecycle.ViewModel
import com.michael.dinnerly.util.Connectivity

class CustomViewModel: ViewModel() {
    private var list = arrayListOf("")
    private var customList: List<CustomModel> = list.map { CustomModel(it) }

    fun setList(number: Int){
        list.clear()
        for(i in 0 until number)
            list.add("")
        resetCustom()
    }

    fun allFieldsFilled(): Boolean{
        for(category in customList){
            if(category.title == "")
                return false
        }
        return true
    }

    fun getCustom(): List<CustomModel> = customList
    private fun resetCustom(){ customList = list.map { CustomModel(it) } }

    // Determine if application is online
    fun isConnected(): Boolean = Connectivity.isOnline
}