package com.sal3awy

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.sal3awy.recyclerviewadapter.BR
import java.util.*

class GenericRecyclerViewAdapter<T>(
    private val clickCallback: ItemRecyclerViewCallback = object : ItemRecyclerViewCallback {},
    private var list: List<T>,
    private val layoutId: Int
) :
    RecyclerView.Adapter<GenericRecyclerViewAdapter.ViewHolder>() {

    private val selectionMap: HashMap<T, Boolean> = HashMap()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val bind: ViewDataBinding =
            DataBindingUtil.bind(LayoutInflater.from(parent.context).inflate(viewType, parent, false))!!
        return ViewHolder(bind)
    }

    override fun getItemCount() = list.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val model: T = list[position]
        holder.binding.setVariable(BR.model, model)
        try {
            holder.binding.setVariable(BR.callback, clickCallback)
        } catch (ex: ClassCastException) {
            throw Exception("try to call methods in the recyclerView item layout which are not found in the callback variable.")
        }
        holder.binding.setVariable(BR.isSelected, selectionMap[model])
        holder.binding.executePendingBindings()
    }

    override fun getItemViewType(position: Int): Int {
        return layoutId
    }

    fun setItemSelected(item: T, multiSelect: Boolean) {
        if (!multiSelect) {
            for (key: T in selectionMap.keys) {
                selectionMap[key] = false
            }
        }

        selectionMap[item] = !selectionMap[item]!!
        notifyDataSetChanged()
    }

    fun submitList(list: List<T>) {
        this.list = list
        for (item: T in list) {
            selectionMap[item] = false
        }
        notifyDataSetChanged()
    }

    class ViewHolder(val binding: ViewDataBinding) :
        RecyclerView.ViewHolder(binding.root)
}

interface ItemRecyclerViewCallback