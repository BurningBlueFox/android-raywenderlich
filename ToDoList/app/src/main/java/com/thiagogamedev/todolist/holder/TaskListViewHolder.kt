package com.thiagogamedev.todolist.holder

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.thiagogamedev.todolist.R

class TaskListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val taskTextView = itemView?.findViewById<TextView>(R.id.textview_task) as TextView
}