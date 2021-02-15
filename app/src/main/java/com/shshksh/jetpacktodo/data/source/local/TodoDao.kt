package com.shshksh.jetpacktodo.data.source.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.shshksh.jetpacktodo.data.entity.Todo

@Dao
interface TodoDao {
    @Insert
    fun saveTodo(todo: Todo)

    @Query("select * from Todo")
    fun getAllTodo(): List<Todo>
}