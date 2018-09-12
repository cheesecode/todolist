package com.cheesecode.android.todolist.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "todo_table")
class Todo(
        @field:PrimaryKey(autoGenerate = true)
        val id: Int,

        @field:ColumnInfo(name = "todo")
        val todo: String)