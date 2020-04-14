package com.thiagogamedev.todolist.manager

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.preference.PreferenceManager
import com.thiagogamedev.todolist.model.TaskList

class ListDataManager(app: Application) : AndroidViewModel(app) {

    private val context = app.applicationContext
    fun saveList(taskList: TaskList) {
        val sharedPref = PreferenceManager.getDefaultSharedPreferences(context).edit()
        sharedPref.putStringSet(taskList.name, taskList.tasks.toHashSet())
        sharedPref.apply()
        Log.d(
            "TAG_DATA_MANAGER",
            "Saving parameters: ${taskList.name} containing ${taskList.tasks.size} items"
        )
    }

    fun readList(): ArrayList<TaskList> {
        val sharedPref = PreferenceManager.getDefaultSharedPreferences(context)
        val content = sharedPref.all
        val lists = ArrayList<TaskList>()

        for (item in content) {
            val taskItem = ArrayList(item.value as HashSet<String>)
            val list =
                TaskList(item.key, taskItem)
            lists.add(list)
        }
        return lists
    }
}