package com.shshksh.jetpacktodo.ui.main

import com.shshksh.jetpacktodo.data.entity.Todo

class TodoItem(val todo: Todo, val clickListener: TodoClickListener) {

    fun getTitle() = todo.title

    fun getDescription() = todo.description

    interface TodoClickListener {
        fun onTodoClick(todoItem: TodoItem)
    }

}