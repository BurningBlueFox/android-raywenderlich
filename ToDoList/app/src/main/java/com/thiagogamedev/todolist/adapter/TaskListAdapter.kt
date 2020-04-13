package com.thiagogamedev.todolist.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.thiagogamedev.todolist.R
import com.thiagogamedev.todolist.model.TaskList
import com.thiagogamedev.todolist.holder.TaskListViewHolder

class TaskListAdapter(var list: TaskList) : RecyclerView.Adapter<TaskListViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskListViewHolder {
        val viewHolder = LayoutInflater.from(parent?.context)
            .inflate(R.layout.task_view_holder, parent, false)
        return TaskListViewHolder(viewHolder)
    }

    override fun getItemCount(): Int = list.tasks.size

    override fun onBindViewHolder(holder: TaskListViewHolder, position: Int) {
        holder.taskTextView?.text = list.tasks[position]
    }
}