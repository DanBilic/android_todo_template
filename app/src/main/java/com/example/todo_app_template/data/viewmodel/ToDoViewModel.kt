package com.example.todo_app_template.data.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.todo_app_template.data.ToDoDatabase
import com.example.todo_app_template.data.models.ToDoData
import com.example.todo_app_template.data.repository.ToDoRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ToDoViewModel(application: Application):AndroidViewModel(application) {
    private val toDoDao = ToDoDatabase.getDatabase(application).toDoDao()
    private val repository: ToDoRepository

    val getALlData : LiveData<List<ToDoData>>

    init {
        repository = ToDoRepository(toDoDao)
        getALlData = repository.getAllData
    }

    fun insertData(toDoData: ToDoData){
        // start coroutines in view model scope
        // view model scope is part of kotlin coroutines
        viewModelScope.launch(Dispatchers.IO) {
            repository.inserData(toDoData)
        }
    }

    fun updateData(toDoData: ToDoData){
        viewModelScope.launch (Dispatchers.IO){
            repository.updateData(toDoData)
        }
    }

    fun deleteItem(toDoData: ToDoData){
        viewModelScope.launch (Dispatchers.IO){
            repository.deleteItem(toDoData)
        }
    }

    fun deleteAll(){
        viewModelScope.launch (Dispatchers.IO){
            repository.deleteAll()
        }
    }
}