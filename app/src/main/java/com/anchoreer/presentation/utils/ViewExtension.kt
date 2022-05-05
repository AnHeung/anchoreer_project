package com.anchoreer.presentation.utils

import android.content.ContextWrapper
import android.view.View
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.anchoreer.presentation.R
import com.squareup.picasso.Picasso
import timber.log.Timber


fun View.getParentActivity(): AppCompatActivity? {

    val context = this.context

    while (context is ContextWrapper) {
        if (context is AppCompatActivity) {
            return context
        }
    }
    return null
}

fun View.makeVisible() {
    visibility = View.VISIBLE
}

fun View.makeGone() {
    visibility = View.GONE
}

fun ImageView.setImageToUrl(imageUrl: String?) {
    try {
        Picasso.get()
            .load(imageUrl)
            .fit()
            .error(R.drawable.no_image)
            .into(this)
    }catch (e : Exception){
        Timber.e("setImageToUrl Exception: $e")
    }
}