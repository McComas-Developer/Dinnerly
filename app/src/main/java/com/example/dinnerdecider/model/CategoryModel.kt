package com.example.dinnerdecider.model

data class CategoryModel(
    val title: String,
    val desc: String,
    var isClicked: Boolean = false,
    var isExpanded: Boolean = false
)