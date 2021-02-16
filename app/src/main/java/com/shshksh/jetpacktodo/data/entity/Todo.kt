package com.shshksh.jetpacktodo.data.entity

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity
data class Todo(
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,
    val title: String,
    val description: String,
//    val created: Long,
//    val lastModified: Long
) : Parcelable