package com.shshksh.jetpacktodo.ui.add

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import androidx.room.Room
import com.shshksh.jetpacktodo.data.entity.Todo
import com.shshksh.jetpacktodo.data.source.TodoRepository
import com.shshksh.jetpacktodo.data.source.local.TodoDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AddViewModel(private val context: Application) : AndroidViewModel(context) {
    private val repo = obtainRepository()
    val title = MutableLiveData<String>()
    val description = MutableLiveData<String>()

    fun save() {
        Log.d(
            this::class.simpleName,
            "save: title: ${title.value}, description: ${description.value}"
        )
        viewModelScope.launch(Dispatchers.IO) {
            repo.saveTodo(Todo(title = title.value!!, description = description.value!!))
        }
    }

    private fun obtainRepository() = TodoRepository(
        Room.databaseBuilder(
            context,
            TodoDatabase::class.java,
            "todo-db"
        ).build()
    )
}