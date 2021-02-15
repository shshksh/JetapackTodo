package com.shshksh.jetpacktodo.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Todo(
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,
    val title: String,
    val description: String,
//    val created: Long,
//    val lastModified: Long
)