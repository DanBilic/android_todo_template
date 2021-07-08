package com.example.todo_app_template.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface ToDoDao {

    // room librry works with livedata
    @Query("select * from todo_table order by id asc")
    fun getAllData(): LiveData<List<ToDoData>>

    // suspend -> insertData will run in a coroutine
    @Insert(onConflict= OnConflictStrategy.IGNORE)
    suspend fun insertData(toDoData: ToDoData)
}