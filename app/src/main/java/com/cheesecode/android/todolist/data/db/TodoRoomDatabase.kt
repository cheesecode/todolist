package com.cheesecode.android.todolist.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.cheesecode.android.todolist.data.db.dao.TodoDao
import com.cheesecode.android.todolist.model.Todo

@Database(entities = arrayOf(Todo::class), version = 1)
abstract class TodoRoomDatabase : RoomDatabase() {

    abstract fun todoDao(): TodoDao


    companion object {

        private var INSTANCE: TodoRoomDatabase? = null
        private val sRoomDatabaseCallback = object : RoomDatabase.Callback() {

            override fun onOpen(db: SupportSQLiteDatabase) {
                super.onOpen(db)


            }
        }

        internal fun getDatabase(context: Context): TodoRoomDatabase? {
            if (INSTANCE == null) {
                synchronized(TodoRoomDatabase::class.java) {
                    if (INSTANCE == null) {
                        INSTANCE = Room.databaseBuilder(context.applicationContext,
                                TodoRoomDatabase::class.java, "todo_database")
                                .addCallback(sRoomDatabaseCallback)
                                .build()

                    }
                }
            }
            return INSTANCE
        }
    }

}