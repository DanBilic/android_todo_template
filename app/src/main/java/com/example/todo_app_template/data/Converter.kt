package com.example.todo_app_template.data

import androidx.room.TypeConverter
import com.example.todo_app_template.data.models.Priority


class Converter {

    @TypeConverter
    fun fromPriority(priority: Priority): String{
        return priority.name
    }

    @TypeConverter
    fun toPriority(priority: String): Priority {
        return Priority.valueOf(priority)
    }
}