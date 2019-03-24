package com.cheesecode.android.todolist.data

import android.app.Application
import androidx.lifecycle.LiveData
import com.cheesecode.android.todolist.data.db.TodoRoomDatabase
import com.cheesecode.android.todolist.data.db.dao.TodoDao
import com.cheesecode.android.todolist.model.Todo
import com.pawegio.kandroid.runAsync


class TodoRepository constructor(application: Application) {

    private val todoDao: TodoDao
    val allTodos: LiveData<List<Todo>>

    init {
        val db = TodoRoomDatabase.getDatabase(application)
        todoDao = db?.todoDao()!! //todo
        allTodos = todoDao.getAllWords()
    }

    fun insert(todo: Todo) {
        runAsync {
            todoDao.insert(todo)
        }
    }

    fun deleteAll() {
        runAsync {
            todoDao.deleteAll()
        }
    }

}