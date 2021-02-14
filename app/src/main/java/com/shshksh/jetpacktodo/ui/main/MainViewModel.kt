package com.shshksh.jetpacktodo.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {
    private val todoList = listOf<String>()
    val isEmpty: LiveData<Boolean> = MutableLiveData(todoList.isEmpty())
}