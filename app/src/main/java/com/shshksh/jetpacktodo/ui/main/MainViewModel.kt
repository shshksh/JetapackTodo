package com.shshksh.jetpacktodo.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shshksh.jetpacktodo.data.entity.Todo
import com.shshksh.jetpacktodo.data.source.TodoRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel(
    private val repo: TodoRepository
) : ViewModel() {
    lateinit var liveTodo: LiveData<List<Todo>>

    fun loadTodo() {
        viewModelScope.launch(Dispatchers.IO) {
            liveTodo = repo.getAllTodo()
        }
    }
}