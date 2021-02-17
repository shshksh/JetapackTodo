package com.shshksh.jetpacktodo.ui.add

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shshksh.jetpacktodo.data.entity.Todo
import com.shshksh.jetpacktodo.data.source.TodoRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AddViewModel(private val repo: TodoRepository) : ViewModel() {
    private var id: Int = -1
    val title = MutableLiveData<String>()
    val description = MutableLiveData<String>()

    fun init(id: Int) {
        this.id = id
        if (id >= 0) {
            viewModelScope.launch(Dispatchers.IO) {
                val todo = repo.getTodo(id)
                title.postValue(todo.title)
                description.postValue(todo.description)
            }
        }
    }

    fun save() {
        viewModelScope.launch(Dispatchers.IO) {
            repo.saveTodo(Todo(title = title.value!!, description = description.value!!))
        }
    }

    fun update() {
        viewModelScope.launch(Dispatchers.IO) {
            repo.updateTodo(Todo(id, title.value!!, description.value!!))
        }
    }
}