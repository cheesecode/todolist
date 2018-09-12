package com.cheesecode.android.todolist.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.cheesecode.android.todolist.data.TodoRepository
import com.cheesecode.android.todolist.model.Todo


class TodoViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: TodoRepository = TodoRepository(application)

    val allTodos: LiveData<List<Todo>>

    init {
        allTodos = repository.allTodos
    }

    fun insert(todo: Todo) {
        repository.insert(todo)
    }

    fun deleteAll() {
        repository.deleteAll()
    }
}