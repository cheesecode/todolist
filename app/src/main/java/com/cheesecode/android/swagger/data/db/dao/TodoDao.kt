package com.cheesecode.android.todolist.data.db.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.cheesecode.android.todolist.model.Todo


@Dao
interface TodoDao {

    @Query("SELECT * from todo_table ORDER BY todo ASC")
    fun getAllWords(): LiveData<List<Todo>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(todo: Todo)

    @Query("DELETE FROM todo_table")
    fun deleteAll()
}
