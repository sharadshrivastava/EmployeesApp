package com.test.app.ui.common

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

@BindingAdapter("downloadUrl")
fun loadImage(imageView: ImageView, url: String?) = Glide.with(imageView).load(url).into(imageView)

@BindingAdapter(value = ["items", "itemLayout", "clickListener"], requireAll = true)
fun <T> configureRecyclerView(
    recyclerView: RecyclerView,
    items: List<Any>?,
    layoutId: Int,
    clickListener: ItemClickListener
) {
    if (recyclerView.adapter == null) {
        recyclerView.adapter = GenericRecyclerViewAdapter(items, layoutId, clickListener)
    } else {
        (recyclerView.adapter as GenericRecyclerViewAdapter).setItems(items)
    }
}