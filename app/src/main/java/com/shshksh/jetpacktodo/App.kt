package com.shshksh.jetpacktodo

import android.app.Application
import androidx.room.Room
import com.shshksh.jetpacktodo.data.source.local.TodoDatabase
import com.shshksh.jetpacktodo.util.AppViewModelFactory

class App : Application() {
    private val todoDB: TodoDatabase by lazy {
        Room.databaseBuilder(
            this, TodoDatabase::class.java, "todo-db"
        ).build()
    }
    val viewModelFactory: AppViewModelFactory by lazy { AppViewModelFactory(todoDB) }

    override fun onCreate() {
        super.onCreate()
    }
}