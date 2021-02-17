package com.shshksh.jetpacktodo.util

import androidx.recyclerview.widget.DiffUtil
import com.shshksh.jetpacktodo.ui.main.TodoItem

class TodoDiffCallback(private val oldItems: List<TodoItem>, private val newItems: List<TodoItem>) :
    DiffUtil.Callback() {
    override fun getOldListSize(): Int {
        return oldItems.size
    }

    override fun getNewListSize(): Int {
        return newItems.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldItems[oldItemPosition].todo.id == newItems[newItemPosition].todo.id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return true
    }
}