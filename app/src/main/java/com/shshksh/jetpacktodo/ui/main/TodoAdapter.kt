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
import com.shshksh.jetpacktodo.data.entity.Todo

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

    private var items: MutableList<Todo> = ArrayList()

    override fun getItemViewType(position: Int): Int {
        return R.layout.item_todo
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {
        return TodoViewHolder(parent.context, viewType)
    }

    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
        holder.binding.setVariable(BR.todo, items[position])
        holder.binding.executePendingBindings()
    }

    override fun getItemCount() = items.size

    fun setItems(items: List<Todo>) {
        this.items.clear()
        this.items.addAll(items)
        notifyDataSetChanged()
    }
}