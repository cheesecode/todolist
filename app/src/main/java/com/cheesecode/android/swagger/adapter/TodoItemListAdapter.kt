package com.cheesecode.android.todolist.adapter

import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.cheesecode.android.R
import com.cheesecode.android.todolist.extensions.inflate
import com.cheesecode.android.todolist.model.Todo


class TodoItemListAdapter : androidx.recyclerview.widget.RecyclerView.Adapter<TodoItemListAdapter.TodoItemViewHolder>() {


    private var items: List<Todo>? = null // Cached copy of items

    class TodoItemViewHolder constructor(itemView: View) : androidx.recyclerview.widget.RecyclerView.ViewHolder(itemView) {
        val todoItemView: TextView = itemView.findViewById(R.id.textView)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoItemViewHolder {
        val itemView = parent.inflate(R.layout.recyclerview_item)
        return TodoItemViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: TodoItemViewHolder, position: Int) {
        if (items != null) {
            val current = items?.get(position)
            holder.todoItemView.text = current?.todo
        } else {
            // Covers the case of data not being ready yet.
            holder.todoItemView.text = "No item"
        }
    }

    internal fun setWords(todos: List<Todo>) {
        this.items = todos
        notifyDataSetChanged()
    }

    // getItemCount() is called many times, and when it is first called,
    // items has not been updated (means initially, it's null, and we can't return null).
    override fun getItemCount(): Int {
        return if (items != null)
            items!!.size
        else
            0
    }
}