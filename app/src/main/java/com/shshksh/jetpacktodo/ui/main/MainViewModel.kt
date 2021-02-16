package com.shshksh.jetpacktodo.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.shshksh.jetpacktodo.data.source.TodoRepository

class MainViewModel(
    val repo: TodoRepository
) : ViewModel() {
    private val todoList = listOf<String>()
    val isEmpty: LiveData<Boolean> = MutableLiveData(todoList.isEmpty())
}