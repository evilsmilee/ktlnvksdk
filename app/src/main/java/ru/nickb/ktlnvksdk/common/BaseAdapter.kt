package ru.nickb.ktlnvksdk.common

import android.view.ViewGroup
import androidx.collection.ArrayMap
import androidx.recyclerview.widget.RecyclerView
import ru.nickb.ktlnvksdk.model.view.BaseViewModel
import ru.nickb.ktlnvksdk.ui.holder.BaseViewHolder
import java.util.ArrayList

class BaseAdapter: RecyclerView.Adapter<BaseViewHolder<BaseViewModel>>() {

   private var list: ArrayList<BaseViewModel> = ArrayList()

   private var mTypeInstances: ArrayMap<Int, BaseViewModel> = ArrayMap()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BaseViewHolder<BaseViewModel> {
        return mTypeInstances[viewType]!!.createViewHolder(parent)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: BaseViewHolder<BaseViewModel>, position: Int) {
        holder.bindViewHolder(getItem(position))
    }

    override fun onViewRecycled(holder: BaseViewHolder<BaseViewModel>) {
        super.onViewRecycled(holder)
        holder.unbindViewHolder()
    }

    override fun getItemViewType(position: Int): Int {
        return getItem(position).getType().getValue()
    }

    fun getItem(position: Int): BaseViewModel {
        return list[position]
    }

    fun registerTypeInstance(item: BaseViewModel) {
        if(!mTypeInstances.containsKey(item.getType().getValue())) {
            mTypeInstances.put(item.getType().getValue(), item)
        }
    }

    fun addItems(newItems: ArrayList<out BaseViewModel>) {
        for (newItem: BaseViewModel in newItems) {
            registerTypeInstance(newItem)
        }

        list.addAll(newItems)

        notifyDataSetChanged()
    }

    fun setItems(items: ArrayList<BaseViewModel>) {
        clearList()
        addItems(items)
    }

    fun clearList() {
        list.clear()
    }



}




