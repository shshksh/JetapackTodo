package com.shshksh.jetpacktodo.data.source

import androidx.lifecycle.LiveData
import com.shshksh.jetpacktodo.data.entity.Todo

interface BaseRepository {
    suspend fun saveTodo(todo: Todo)

    suspend fun getAllTodo(): LiveData<List<Todo>>
}