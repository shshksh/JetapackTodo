package com.shshksh.jetpacktodo.data.source

import com.shshksh.jetpacktodo.data.entity.Todo
import com.shshksh.jetpacktodo.data.source.local.TodoDatabase

class TodoRepository(private val db: TodoDatabase) : BaseRepository {

    override fun saveTodo(todo: Todo) {
        db.todoDAO().saveTodo(todo)
    }

    override fun getAllTodo(): List<Todo> {
        return db.todoDAO().getAllTodo()
    }
}