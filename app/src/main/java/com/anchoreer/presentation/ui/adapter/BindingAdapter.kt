package com.anchoreer.presentation.ui.adapter

import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.anchoreer.presentation.R
import com.anchoreer.presentation.utils.makeGone
import com.anchoreer.presentation.utils.makeVisible
import com.anchoreer.presentation.utils.setImageToUrl

object ViewBinding {
    //프로그래스바 처리를 위한 바인딩 어댑터
    @JvmStatic
    @BindingAdapter("isProgress")
    fun setProgress(view: View, isProgress: Boolean) {
        view.run {
            if (isProgress) makeVisible()
            else makeGone()
        }
    }

    @JvmStatic
    @BindingAdapter("setBookmark")
    fun setBookmark(view: ImageView, isLiked: Boolean) {
        val imageResource = if (isLiked) R.drawable.favorite_on_24 else R.drawable.favorite_off_24
        view.setImageResource(imageResource)
    }

    @JvmStatic
    @BindingAdapter("setImageUrl")
    fun setImageToUrl(view: ImageView,imageUrl: String?) {
        view.setImageToUrl(imageUrl)
    }
}