package com.thiagogamedev.todolist.fragment

import android.os.Bundle
import android.text.InputType
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.thiagogamedev.todolist.R
import com.thiagogamedev.todolist.adapter.TodoListAdapter
import com.thiagogamedev.todolist.manager.ListDataManager
import com.thiagogamedev.todolist.model.TaskList
import kotlinx.android.synthetic.main.fragment_todo_list.*

class TodoListFragment : Fragment(), TodoListAdapter.ListClickListener {

    private lateinit var todoListRecyclerView: RecyclerView
    private lateinit var listDataManager: ListDataManager

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_todo_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        activity?.let {
            listDataManager = ViewModelProviders.of(this).get(ListDataManager::class.java)
        }

        val lists = listDataManager.readList()
        todoListRecyclerView = view.findViewById(R.id.lists_reciclerview)
        todoListRecyclerView.layoutManager = LinearLayoutManager(activity)
        todoListRecyclerView.adapter = TodoListAdapter(lists, this)

        fab.setOnClickListener {

            showCreateTodoListDialog()
        }
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
        showTaskListItem(taskList)
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

    private fun showCreateTodoListDialog() {
        activity?.let {
            val dialogTitle = getString(R.string.createListTitle)
            val positiveButtonTitle = getString(R.string.positiveCreateButton)
            val todoTitleEditText = EditText(it)
            val alertDialog = AlertDialog.Builder(it)
            todoTitleEditText.inputType =
                InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_FLAG_CAP_WORDS

            alertDialog.run {
                setTitle(dialogTitle)
                setView(todoTitleEditText)
                setPositiveButton(positiveButtonTitle) { dialog, _ ->

                    val input = todoTitleEditText.text.toString()
                    val list = TaskList(input)
                    addList(list)

                    showTaskListItem(list)

                    dialog.dismiss()
                }
                create()
                show()
            }
        }
    }

    private fun showTaskListItem(list: TaskList) {
        view?.let {
            val action = TodoListFragmentDirections.actionTodoListFragmentToTaskDetailFragment(list.name)
            it.findNavController().navigate(action)
        }
    }

    private fun updateLists() {
        val lists = listDataManager.readList()
        todoListRecyclerView.adapter = TodoListAdapter(lists, this)
    }
}
