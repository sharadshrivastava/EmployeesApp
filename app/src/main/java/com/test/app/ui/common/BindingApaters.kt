package com.test.app.ui.common

import android.widget.ImageView
import android.widget.TextView
import androidx.core.text.HtmlCompat
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import java.text.SimpleDateFormat
import java.util.*

@BindingAdapter("downloadUrl")
fun loadImage(imageView: ImageView, url: String?) = Glide.with(imageView).load(url).into(imageView)

@BindingAdapter("formattedDate")
fun formatDate(textView: TextView, dateStr: String?) {
    val sdfFrom = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())
    val sdfTo = SimpleDateFormat("MMM d, yyyy hh:mm a", Locale.getDefault())

    dateStr?.let {
        sdfFrom.parse(dateStr)?.let { textView.text = sdfTo.format(it) }
    }
}