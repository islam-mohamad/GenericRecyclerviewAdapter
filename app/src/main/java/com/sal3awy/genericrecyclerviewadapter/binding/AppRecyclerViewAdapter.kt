package com.sal3awy.genericrecyclerviewadapter.binding

import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.support.annotation.NonNull
import android.support.v7.recyclerview.extensions.ListAdapter
import android.support.v7.util.DiffUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.android.databinding.library.baseAdapters.BR

class AppRecyclerViewAdapter<T>(
    diffCallback: DiffUtil.ItemCallback<T>
) :
    ListAdapter<T, AppRecyclerViewAdapter.ViewHolder<ViewDataBinding>>(diffCallback) {

    private var layoutId: Int? = null
    private var clickCallback: RecyclerViewCallback? = null

    constructor(clickCallback: RecyclerViewCallback, diffCallback: DiffUtil.ItemCallback<T>) : this(diffCallback) {
        this.clickCallback = clickCallback
    }


    @NonNull
    override fun onCreateViewHolder(@NonNull parent: ViewGroup, viewType: Int): ViewHolder<ViewDataBinding> {
        val bind: ViewDataBinding? =
            DataBindingUtil.bind(LayoutInflater.from(parent.context).inflate(viewType, parent, false))
        return ViewHolder(bind!!)
    }

    @NonNull
    override fun onBindViewHolder(@NonNull holder: ViewHolder<ViewDataBinding>, position: Int) {
        val model: T = getItem(position)
        holder.binding.setVariable(BR.model, model)
        holder.binding.setVariable(BR.callback, clickCallback)
        holder.binding.executePendingBindings()
    }


    class ViewHolder<V : ViewDataBinding>(val binding: V) :
        RecyclerView.ViewHolder(binding.root)

    override fun getItemViewType(position: Int): Int {
        return layoutId!!
    }

    fun setItemViewType(layoutId: Int) {
        this.layoutId = layoutId
    }
}