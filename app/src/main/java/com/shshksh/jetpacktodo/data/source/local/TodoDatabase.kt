package com.shshksh.jetpacktodo.data.source.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.shshksh.jetpacktodo.data.entity.Todo

@Database(entities = [Todo::class], version = 1)
abstract class TodoDatabase : RoomDatabase() {
    abstract fun todoDAO(): TodoDao
}