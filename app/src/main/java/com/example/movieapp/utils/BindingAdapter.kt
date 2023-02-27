package com.example.movieapp

import android.util.Log
import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.movieapp.utils.Constants
import com.squareup.picasso.Picasso
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

@BindingAdapter("app:items")
fun <T> setRecycleViewItem(view: RecyclerView, item: List<T>?) {
    Log.e("TAG", item.toString()+"1")
    if (item != null) {
        (view.adapter as BaseAdapter<T>?)?.setItems(item)
    } else {
        (view.adapter as BaseAdapter<T>?)?.setItems(emptyList())

    }
    }

@BindingAdapter(value = ["app:imageUrl"])
fun setImageFromUrl(view: ImageView, url: String) {
    Glide.with(view).load(Constants.IMAGE_URL + url).into(view)
}

@BindingAdapter(value = ["app:showWhenLoading"])
fun <T> showWhenLoading(view: View, state: State<T>?){
    if (state is State.Loading){
        view.visibility = View.VISIBLE
    }else{
        view.visibility = View.GONE
    }
}
@BindingAdapter(value = ["app:showWhenSuccess"])
fun <T> showWhenSuccess(view: View, state: State<T>?){
    if (state is State.Success){
        view.visibility = View.VISIBLE
    }else{
        view.visibility = View.GONE
    }
}