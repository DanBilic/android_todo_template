package com.example.todo_app_template.data.repository

import androidx.lifecycle.LiveData
import com.example.todo_app_template.data.ToDoDao
import com.example.todo_app_template.data.models.ToDoData

class ToDoRepository(private val toDoDao: ToDoDao) {

    val getAllData: LiveData<List<ToDoData>> = toDoDao.getAllData()

    suspend fun inserData(toDoData: ToDoData){
        toDoDao.insertData(toDoData)
    }
}