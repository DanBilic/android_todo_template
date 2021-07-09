package com.example.todo_app_template.fragments

import android.app.Application
import android.text.TextUtils
import android.view.View
import android.widget.AdapterView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.todo_app_template.R
import com.example.todo_app_template.data.models.Priority
import com.example.todo_app_template.data.models.ToDoData
import java.util.*

class SharedViewModel(application: Application): AndroidViewModel(application) {

    // reusable code for multiple fragments goes here

    val emptyDatabase : MutableLiveData<Boolean> = MutableLiveData(true)

    fun checkIfDatabaseEmpty(toDoData: List<ToDoData>){
        emptyDatabase.value = toDoData.isEmpty()
    }

    // listener for multiple fragments
    // object expressions : object{} creates objects of anonymous classes
    val listener: AdapterView.OnItemSelectedListener =
        object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                when(position){
                    0->{(parent?.getChildAt(0) as TextView)
                        .setTextColor(ContextCompat.getColor(application, R.color.red))}
                    1->{(parent?.getChildAt(0) as TextView)
                        .setTextColor(ContextCompat.getColor(application, R.color.yellow))}
                    2->{(parent?.getChildAt(0) as TextView)
                        .setTextColor(ContextCompat.getColor(application, R.color.green))}
                }
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                TODO("Not yet implemented")
            }
        }

     fun verifyDataFromUser(title: String, description: String): Boolean{
        return if(TextUtils.isEmpty(title) || TextUtils.isEmpty(description)){
            false
        }else !(title.isEmpty() || description.isEmpty())
    }

     fun parsePriority(priority: String): Priority {
        return when(priority){
            "High Priority" -> {
                Priority.HIGH}
            "Medium Priority" -> {
                Priority.MEDIUM}
            "Low Priority" -> {
                Priority.LOW}
            else -> Priority.LOW
        }
    }

    fun parsePriorityToInt(priority: Priority): Int{
        return when(priority){
            Priority.HIGH -> 0
            Priority.MEDIUM -> 1
            Priority.LOW -> 2
        }
    }
}