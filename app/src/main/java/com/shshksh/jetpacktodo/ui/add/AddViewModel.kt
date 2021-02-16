package com.shshksh.jetpacktodo.ui.add

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shshksh.jetpacktodo.data.entity.Todo
import com.shshksh.jetpacktodo.data.source.TodoRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AddViewModel(private val repo: TodoRepository) : ViewModel() {
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
}