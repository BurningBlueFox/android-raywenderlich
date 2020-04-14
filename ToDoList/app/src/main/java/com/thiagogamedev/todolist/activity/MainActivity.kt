package com.thiagogamedev.todolist.activity

import android.content.Intent
import android.os.Bundle
import android.text.InputType
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.thiagogamedev.todolist.manager.ListDataManager
import com.thiagogamedev.todolist.R
import com.thiagogamedev.todolist.model.TaskList
import com.thiagogamedev.todolist.adapter.TodoListAdapter
import com.thiagogamedev.todolist.fragment.TodoListFragment

import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        Navigation.findNavController(this, R.id.nav_host_fragment)
    }

    override fun onBackPressed() {
        super.onBackPressed()
        toolbar.title = "ListMaker"
    }

}
