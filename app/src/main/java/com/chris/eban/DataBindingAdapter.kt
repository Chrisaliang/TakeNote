package com.chris.eban

import android.view.View
import android.widget.TextView
import androidx.databinding.BindingAdapter

@BindingAdapter("isGone")
fun bindIsGone(view: TextView, isGone: Boolean) {
    if (isGone){
        view.visibility = View.GONE
    }else{
        view.visibility = View.VISIBLE
    }
}