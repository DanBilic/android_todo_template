package com.example.todo_app_template.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.todo_app_template.data.models.Priority

@Entity(tableName = "todo_table")
data class ToDoData(
    // autogenerate the id
    @PrimaryKey(autoGenerate = true)
    var id: Int,
    var title: String,
    var priority: Priority,
    var description: String
)