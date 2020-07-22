package com.example.dinnerdecider.model

import androidx.lifecycle.ViewModel
import com.example.dinnerdecider.util.Connectivity

class CustomViewModel: ViewModel() {
    private var list = arrayListOf("")
    private var customList: List<CustomModel> = list.map { CustomModel(it) }

    fun setList(number: Int){
        list.clear()
        for(i in 0 until number)
            list.add("")
        resetCustom()
    }
    fun getCustom(): List<CustomModel>{
        return customList
    }
    private fun resetCustom(){
        customList = list.map { CustomModel(it) }
    }
    // Determine if application is online
    fun isConnected(): Boolean{
        return Connectivity.isOnline
    }
}