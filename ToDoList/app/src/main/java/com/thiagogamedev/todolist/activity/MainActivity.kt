package com.thiagogamedev.todolist.activity

import android.content.Intent
import android.os.Bundle
import android.text.InputType
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.thiagogamedev.todolist.manager.ListDataManager
import com.thiagogamedev.todolist.R
import com.thiagogamedev.todolist.model.TaskList
import com.thiagogamedev.todolist.adapter.TodoListAdapter
import com.thiagogamedev.todolist.fragment.TodoListFragment

import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(),
    TodoListFragment.OnFragmentInteractionListener {

    private var todoListFragment = TodoListFragment.newInstance()

    companion object {
        const val INTENT_LIST_KEY = "listIntent"
        const val LIST_DETAIL_REQUEST_CODE = 100
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)


        fab.setOnClickListener {

            showCreateTodoListDialog()
        }

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == LIST_DETAIL_REQUEST_CODE) {
            data?.let {
                val list = data.getParcelableExtra<TaskList>(
                    INTENT_LIST_KEY
                )!!
                todoListFragment.saveList(list)
            }
        }
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun showCreateTodoListDialog() {
        val dialogTitle = getString(R.string.createListTitle)
        val positiveButtonTitle = getString(R.string.positiveCreateButton)
        val todoTitleEditText = EditText(this)
        val alertDialog = AlertDialog.Builder(this)
        todoTitleEditText.inputType =
            InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_FLAG_CAP_WORDS

        alertDialog.run {
            setTitle(dialogTitle)
            setView(todoTitleEditText)
            setPositiveButton(positiveButtonTitle) { dialog, _ ->

                val input = todoTitleEditText.text.toString()
                val list = TaskList(input)
                todoListFragment.addList(list)

                showTaskListItem(list)

                dialog.dismiss()
            }
            create()
            show()
        }

    }

    private fun showTaskListItem(list: TaskList) {
        val taskListIntent = Intent(this, DetailActivity::class.java)
        taskListIntent.putExtra(INTENT_LIST_KEY, list)
        startActivityForResult(
            taskListIntent,
            LIST_DETAIL_REQUEST_CODE
        )
    }

    override fun onTodoListClicked(taskList: TaskList) {
        showTaskListItem(taskList)
    }
}
