package com.shshksh.jetpacktodo.data.source.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.shshksh.jetpacktodo.data.entity.Todo

@Dao
interface TodoDao {
    @Insert
    fun saveTodo(todo: Todo)

    @Query("select * from Todo")
    fun getAllTodo(): LiveData<List<Todo>>

    @Update
    fun updateTodo(todo: Todo)

    @Query("select * from todo where id = :id")
    fun getTodo(id: Int): Todo
}