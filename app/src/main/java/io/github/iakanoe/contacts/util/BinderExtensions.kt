package io.github.iakanoe.contacts.util

import android.view.View
import androidx.appcompat.widget.AppCompatImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

@BindingAdapter("imageUrl")
fun AppCompatImageView.loadImage(imageUrl: String) {
    Glide.with(context).load(imageUrl).placeholder(drawable).into(this)
}

@BindingAdapter("visible")
fun View.setVisible(visible: Boolean) {
    visibility = if (visible) View.VISIBLE else View.GONE
}

@BindingAdapter("invisible")
fun View.isInvisible(invisible: Boolean) {
    visibility = if (invisible) View.GONE else View.VISIBLE
}