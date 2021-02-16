package com.shshksh.jetpacktodo.ui.main

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.shshksh.jetpacktodo.BR
import com.shshksh.jetpacktodo.R

class TodoAdapter : RecyclerView.Adapter<TodoAdapter.TodoViewHolder>() {

    inner class TodoViewHolder(val binding: ViewDataBinding) :
        RecyclerView.ViewHolder(binding.root) {

        constructor(context: Context, @LayoutRes layoutId: Int) : this(
            DataBindingUtil.inflate(
                LayoutInflater.from(context),
                layoutId,
                null,
                false
            )
        )
    }

    private var items: MutableList<TodoItem> = ArrayList()

    override fun getItemViewType(position: Int): Int {
        return R.layout.item_todo
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {
        return TodoViewHolder(parent.context, viewType)
    }

    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
        holder.binding.setVariable(BR.item, items[position])
        holder.binding.executePendingBindings()
    }

    override fun getItemCount() = items.size

    fun setItems(items: List<TodoItem>) {
        this.items.clear()
        this.items.addAll(items)
        notifyDataSetChanged()
    }
}