package com.thiagogamedev.todolist

import android.os.Bundle
import android.text.InputType
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*

class MainActivity : AppCompatActivity() {
    lateinit var todoListRecyclerView: RecyclerView
    val listDataManager = ListDataManager(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        val lists = listDataManager.readList()

        todoListRecyclerView = findViewById(R.id.lists_reciclerview)
        todoListRecyclerView.layoutManager = LinearLayoutManager(this)
        todoListRecyclerView.adapter = TodoListAdapter(lists)

        fab.setOnClickListener { view ->

            val adapter = todoListRecyclerView.adapter as TodoListAdapter
            showCreateTodoListDialog()
//            adapter.addNewItem()
//            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                    .setAction("Action", null).show()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
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
                }
                dialog.dismiss()
            }
            create()
            show()
        }

    }
}
