package com.shshksh.jetpacktodo.data.source

import com.shshksh.jetpacktodo.data.entity.Todo

interface BaseRepository {
    fun saveTodo(todo: Todo)

    fun getAllTodo(): List<Todo>
}