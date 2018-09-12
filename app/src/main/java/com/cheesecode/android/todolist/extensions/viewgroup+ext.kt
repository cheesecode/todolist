package com.cheesecode.android.todolist.extensions

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup


//inflate views (used in adapters)
fun ViewGroup.inflate(layoutRes: Int): View {
    return LayoutInflater.from(context).inflate(layoutRes, this, false)
}