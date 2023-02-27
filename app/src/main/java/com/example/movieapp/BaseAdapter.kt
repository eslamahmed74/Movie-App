package com.example.movieapp

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.databinding.library.baseAdapters.BR
import androidx.recyclerview.widget.RecyclerView

interface BaseClickListener
abstract class BaseAdapter<T>(private var items: List<T>, val listener: BaseClickListener) :
    RecyclerView.Adapter<BaseAdapter.BaseViewHolder>() {

    abstract val layoutId: Int

    override fun getItemCount() = items.size

    fun getItems() = items

    fun setItems(newList:List<T>){
        items=newList
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
        val current=items[position]
        when(holder){
            is ItemViewHolder -> {
                holder.binding.setVariable(BR.movieItem,current)

            }
        }

    }

    class ItemViewHolder(val binding: ViewDataBinding) : BaseViewHolder(binding)
    abstract class BaseViewHolder(binding: ViewDataBinding) : RecyclerView.ViewHolder(binding.root)
}
interface RecyclerViewInteractionListener:BaseClickListener {
    fun <T> onClickItem(view:T)
}