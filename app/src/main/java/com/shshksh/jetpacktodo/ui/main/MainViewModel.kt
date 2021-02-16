package com.shshksh.jetpacktodo.ui.main

import android.util.Log
import androidx.lifecycle.*
import com.shshksh.jetpacktodo.data.source.TodoRepository
import io.reactivex.rxjava3.subjects.PublishSubject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel(
    private val repo: TodoRepository
) : ViewModel(), TodoItem.TodoClickListener {
    lateinit var liveTodo: LiveData<List<TodoItem>>
    val todoClickEvent: PublishSubject<TodoItem> = PublishSubject.create()

    fun loadTodo() {
        viewModelScope.launch(Dispatchers.IO) {
            liveTodo = repo.getAllTodo().map { list ->
                list.map { TodoItem(it, this@MainViewModel) }
            }
        }
    }

    override fun onTodoClick(todoItem: TodoItem) {
        Log.d(this::class.simpleName, "onTodoClick: emit todo item")
        todoClickEvent.onNext(todoItem)
    }
}