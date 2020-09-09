package com.michael.dinnerly.model

data class CategoryModel(
    val title: String,
    val desc: String,
    var isClicked: Boolean = false,
    var isExpanded: Boolean = false
)