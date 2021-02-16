package com.shshksh.jetpacktodo.data.source

import androidx.lifecycle.LiveData
import com.shshksh.jetpacktodo.data.entity.Todo
import com.shshksh.jetpacktodo.data.source.local.TodoDatabase

class TodoRepository(private val db: TodoDatabase) : BaseRepository {

    override suspend fun saveTodo(todo: Todo) {
        db.todoDAO().saveTodo(todo)
    }

    override suspend fun getAllTodo(): LiveData<List<Todo>> {
        return db.todoDAO().getAllTodo()
    }
}