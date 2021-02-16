package com.shshksh.jetpacktodo.data.source

import com.shshksh.jetpacktodo.data.entity.Todo

interface BaseRepository {
    suspend fun saveTodo(todo: Todo)

    suspend fun getAllTodo(): List<Todo>
}