package com.thiagogamedev.todolist.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.thiagogamedev.todolist.R
import com.thiagogamedev.todolist.model.TaskList
import com.thiagogamedev.todolist.holder.TodoListViewHolder


class TodoListAdapter(private val lists: ArrayList<TaskList>, val clickListener: ListClickListener) : RecyclerView.Adapter<TodoListViewHolder>() {

    interface ListClickListener{
        fun listItemClicked(taskList: TaskList)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoListViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.todo_list_view_holder, parent, false)
        return TodoListViewHolder(view)
    }

    override fun getItemCount(): Int = lists.size

    override fun onBindViewHolder(holder: TodoListViewHolder, position: Int) {
        holder.listPositionTextView.text = (position + 1).toString()
        holder.listTitleTextView.text = lists[position].name
        holder.itemView.setOnClickListener {
            clickListener.listItemClicked(lists[position])
        }
    }

    fun addList(list: TaskList) {
        lists.add(list)
        notifyItemInserted(lists.size - 1)
    }
}