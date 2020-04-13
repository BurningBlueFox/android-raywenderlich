package com.thiagogamedev.todolist.fragment

import android.content.Context
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.thiagogamedev.todolist.R
import com.thiagogamedev.todolist.adapter.TodoListAdapter
import com.thiagogamedev.todolist.manager.ListDataManager
import com.thiagogamedev.todolist.model.TaskList

class TodoListFragment : Fragment(), TodoListAdapter.ListClickListener {

    private var listener: OnFragmentInteractionListener? = null
    private lateinit var todoListRecyclerView: RecyclerView
    private lateinit var listDataManager: ListDataManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_todo_list, container, false)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnFragmentInteractionListener) {
            listener = context
            listDataManager = ListDataManager(context)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val lists = listDataManager.readList()

        todoListRecyclerView = view.findViewById(R.id.lists_reciclerview)
        todoListRecyclerView.layoutManager = LinearLayoutManager(activity)
        todoListRecyclerView.adapter = TodoListAdapter(lists, this)
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    interface OnFragmentInteractionListener {
        fun onTodoListClicked(list: TaskList)
    }

    companion object {
        fun newInstance(): TodoListFragment {
            return TodoListFragment()
        }
    }

    override fun listItemClicked(taskList: TaskList) {
        listener?.onTodoListClicked(taskList)
    }

    fun addList(list: TaskList) {
        listDataManager.saveList(list)
        val todoListAdapter = todoListRecyclerView.adapter as TodoListAdapter
        todoListAdapter.addList(list)
    }

    fun saveList(list: TaskList) {
        listDataManager.saveList(list)
        updateLists()
    }

    private fun updateLists() {
        val lists = listDataManager.readList()
        todoListRecyclerView.adapter =
            TodoListAdapter(lists, this)
    }
}