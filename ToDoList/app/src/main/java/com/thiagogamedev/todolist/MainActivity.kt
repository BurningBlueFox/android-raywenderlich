package com.thiagogamedev.todolist

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

import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), TodoListAdapter.ListClickListener {
    companion object {
        const val INTENT_LIST_KEY = "listIntent"
        const val LIST_DETAIL_REQUEST_CODE = 100
    }

    private lateinit var todoListRecyclerView: RecyclerView
    private val listDataManager = ListDataManager(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        val lists = listDataManager.readList()

        todoListRecyclerView = findViewById(R.id.lists_reciclerview)
        todoListRecyclerView.layoutManager = LinearLayoutManager(this)
        todoListRecyclerView.adapter = TodoListAdapter(lists, this)

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
        if(requestCode == LIST_DETAIL_REQUEST_CODE){
            data?.let {
                val list = data.getParcelableExtra<TaskList>(INTENT_LIST_KEY)!!
                listDataManager.saveList(list)
                updateLists()
            }
        }
    }

    private fun updateLists() {
        val lists = listDataManager.readList()
        todoListRecyclerView.adapter = TodoListAdapter(lists, this)
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
                with(todoListRecyclerView.adapter as TodoListAdapter) {
                    val input = todoTitleEditText.text.toString()
                    val list = TaskList(input)
                    listDataManager.saveList(list)
                    this.addList(list)

                    showTaskListItem(list)
                }
                dialog.dismiss()
            }
            create()
            show()
        }

    }

    private fun showTaskListItem(list: TaskList) {
        val taskListIntent = Intent(this, DetailActivity::class.java)
        taskListIntent.putExtra(INTENT_LIST_KEY, list)
        startActivityForResult(taskListIntent, LIST_DETAIL_REQUEST_CODE)
    }

    override fun listItemClicked(taskList: TaskList) {
        showTaskListItem(taskList)
    }
}
