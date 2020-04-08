package com.thiagogamedev.todolist

import android.content.Context
import androidx.preference.PreferenceManager

class ListDataManager(private val context: Context) {
    fun saveList(taskList: TaskList) {
        val sharedPref = PreferenceManager.getDefaultSharedPreferences(context).edit()
        sharedPref.putStringSet(taskList.name, taskList.tasks.toHashSet())
        sharedPref.apply()
    }

    fun readList(): ArrayList<TaskList> {
        val sharedPref = PreferenceManager.getDefaultSharedPreferences(context)
        val content = sharedPref.all
        val lists = ArrayList<TaskList>()

        for (item in content) {
            val taskItem = ArrayList(item.value as HashSet<String>)
            val list = TaskList(item.key, taskItem)
            lists.add(list)
        }
        return lists
    }
}