package com.example.dinnerdecider.db

interface RepositoryOutline {
    fun getQuestions()
    fun getList(): List<String>
}