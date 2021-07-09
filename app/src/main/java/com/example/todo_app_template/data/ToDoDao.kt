package com.example.todo_app_template.data

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.todo_app_template.data.models.ToDoData

@Dao
interface ToDoDao {

    // room librry works with livedata
    @Query("select * from todo_table order by id asc")
    fun getAllData(): LiveData<List<ToDoData>>

    // suspend -> insertData will run in a coroutine
    @Insert(onConflict= OnConflictStrategy.IGNORE)
    suspend fun insertData(toDoData: ToDoData)

    @Update
    suspend fun updateData(toDoData: ToDoData)

    @Delete
    suspend fun deleteItem(toDoData: ToDoData)

    @Query("delete from todo_table")
    suspend fun deleteAll()
}