package com.stepasha.neewsfeedinjector.util

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import com.stepasha.neewsfeedinjector.ui.PostAdapter

//So now, let us add a DataBinder.kt file to the utils package in order to map BindingAdapter methods


// Safe here as method are used by Data binding
@Suppress("unused")

// ...

/**
 * Sets an adapter to a RecyclerView (to be used in view with one RecyclerView)
 * @param view the RecyclerView on which to set the adapter
 * @param adapter the adapter to set to the RecyclerView
 */
@BindingAdapter("adapter")
internal fun setAdapter(view: RecyclerView, adapter: PostAdapter) {
    view.adapter = adapter
}

/**
 * Sets a LayoutManager to a RecyclerView (to be used in view with one RecyclerView)
 * @param view the RecyclerView on which to set the LayoutManager
 * @param layoutManager the LayoutManager to set to the RecyclerView
 */
@BindingAdapter("layoutManager")
fun setLayoutManager(view: RecyclerView, layoutManager: RecyclerView.LayoutManager) {
    view.layoutManager = layoutManager
}

/**
 * Adds a DividerItemDecoration to a RecyclerView (to be used in view with one RecyclerView)
 * @param view the RecyclerView on which to set the DividerItemDecoration
 * @param dividerItemDecoration the DividerItemDecoration to set to the RecyclerView
 */
@BindingAdapter("dividerItemDecoration")
fun setDividerItemDecoration(view: RecyclerView, dividerItemDecoration: DividerItemDecoration) {
    view.addItemDecoration(dividerItemDecoration)
}