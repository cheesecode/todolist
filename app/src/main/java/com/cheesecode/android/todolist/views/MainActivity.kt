package com.cheesecode.android.todolist.views

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.cheesecode.android.R
import com.cheesecode.android.todolist.adapter.TodoItemListAdapter
import com.cheesecode.android.todolist.model.Todo
import com.cheesecode.android.todolist.viewmodel.TodoViewModel
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*

private const val NEWWITEMREQUESTCODE = 1

class MainActivity : AppCompatActivity() {

    private val todoAdapter = TodoItemListAdapter()
    private lateinit var vm: TodoViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        initUI()
    }

    private fun initUI() {

        vm = ViewModelProviders.of(this).get(TodoViewModel::class.java)
        vm.allTodos.observe(this, Observer { todos ->

            todos?.let {
                todoAdapter.setWords(todos)
            }
        })

        fab?.setOnClickListener {
            val intent = Intent(this, NewTodoActivity::class.java)
            startActivityForResult(intent, NEWWITEMREQUESTCODE)
        }

        recyclerview?.apply {
            adapter = todoAdapter
            layoutManager = androidx.recyclerview.widget.LinearLayoutManager(context)
        }

    }

    public override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == NEWWITEMREQUESTCODE && resultCode == Activity.RESULT_OK) {
            val todo = Todo(0, data?.getStringExtra(NewTodoActivity.EXTRA_REPLY) ?: "")
            vm.insert(todo)
        } else {
            Toast.makeText(this, R.string.empty_not_saved, Toast.LENGTH_LONG).show()
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
            R.id.action_settings -> {
                vm.deleteAll()
                return true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

}
