package com.shshksh.jetpacktodo.util

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.shshksh.jetpacktodo.data.source.TodoRepository
import com.shshksh.jetpacktodo.data.source.local.TodoDatabase
import com.shshksh.jetpacktodo.ui.add.AddViewModel
import com.shshksh.jetpacktodo.ui.main.MainViewModel

class AppViewModelFactory(private val db: TodoDatabase) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(MainViewModel::class.java) -> {
                MainViewModel(TodoRepository(db)) as T
            }
            modelClass.isAssignableFrom(AddViewModel::class.java) -> {
                AddViewModel(TodoRepository(db)) as T
            }
            else -> {
                throw IllegalArgumentException()
            }
        }
    }
}