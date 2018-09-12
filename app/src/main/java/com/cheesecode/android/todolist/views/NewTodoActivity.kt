package com.cheesecode.android.todolist.views

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import androidx.appcompat.app.AppCompatActivity
import com.cheesecode.android.R
import kotlinx.android.synthetic.main.activity_new_todo_item.*


class NewTodoActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_new_todo_item)

        initUI()
    }

    private fun initUI() {

        button_save.setOnClickListener {
            val replyIntent = Intent()
            if (TextUtils.isEmpty(edit_todo.text)) {
                setResult(Activity.RESULT_CANCELED, replyIntent)
            } else {
                val todo = edit_todo.text.toString()
                replyIntent.putExtra(EXTRA_REPLY, todo)
                setResult(Activity.RESULT_OK, replyIntent)
            }
            finish()
        }
    }

    companion object {
        const val EXTRA_REPLY = "extrareply"
    }
}

