package com.example.movieapp.utils

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.databinding.library.baseAdapters.BR
import androidx.recyclerview.widget.RecyclerView
import com.example.local.MovieEntity

interface BaseClickListener

/*
, val listener: BaseClickListener
 */
abstract class BaseAdapter<T>(private var items: List<T>) :
    RecyclerView.Adapter<BaseAdapter.BaseViewHolder>() {

    abstract val layoutId: Int

    private var onItemClickListener: ((T) -> Unit)? = null

    fun setOnItemClickListener(listener: (T) -> Unit) {
        onItemClickListener = listener
    }

    override fun getItemCount() = items.size

    fun getItems() = items

    fun setItems(newList: List<T>) {
        items = newList
    }

    @SuppressLint("NotifyDataSetChanged")
    fun updateItems(newList: List<T>) {
        items = newList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        return ItemViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                layoutId,
                parent,
                false
            )
        )

    }

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        val current = items[position]
        when (holder) {
            is ItemViewHolder -> {
                holder.binding.setVariable(BR.movieItem, current)
                holder.binding.root.setOnClickListener { onItemClickListener?.invoke(current) }
                holder.binding.setVariable(BR.movieListItem, current)
                holder.binding.setVariable(BR.settingItems, current)
            }
        }
    }

    class ItemViewHolder(val binding: ViewDataBinding) : BaseViewHolder(binding)
    abstract class BaseViewHolder(binding: ViewDataBinding) : RecyclerView.ViewHolder(binding.root)
}

interface RecyclerViewInteractionListener : BaseClickListener {
    fun <T> onClickItem(view: T)
}

