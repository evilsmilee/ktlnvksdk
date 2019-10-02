package ru.nickb.ktlnvksdk.common.manager

import android.util.ArrayMap
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.nickb.ktlnvksdk.model.view.BaseViewModel
import ru.nickb.ktlnvksdk.ui.holder.BaseViewHolder
import java.util.*



class BaseAdapter: RecyclerView.Adapter<BaseViewHolder<BaseViewModel>>() {

    private val list = ArrayList<BaseViewModel>()

    private val mTypeInstances:ArrayMap<Int, BaseViewModel> = ArrayMap()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BaseViewHolder<BaseViewModel> {
        return mTypeInstances[viewType]!!.createViewHolder(parent) as BaseViewHolder<BaseViewModel>
    }

    override fun onBindViewHolder(holder: BaseViewHolder<BaseViewModel>, position: Int) {
        holder.bindViewHolder(getItem(position))
    }

    override fun onViewRecycled(holder: BaseViewHolder<BaseViewModel>) {
        super.onViewRecycled(holder)
        holder.unbindViewHolder()
    }

    fun registerTypeInstance(item: BaseViewModel) {
        if (!mTypeInstances.containsKey(item.type.value)) {
            mTypeInstances[item.type.value] = item
        }
    }


    fun setItems(items: List<BaseViewModel>) {
        clearList()
        addItems(items)
    }

    fun addItems(newItems: List<BaseViewModel>) {

        for (newItem in newItems) {
            registerTypeInstance(newItem)
        }

        list.addAll(newItems)

        notifyDataSetChanged()
    }


    fun clearList() {
        list.clear()
    }


    override fun getItemViewType(position: Int): Int {
        return getItem(position).type.value
    }

    override fun getItemCount(): Int {
        return list.size
    }


    fun getItem(position: Int): BaseViewModel {
        return list[position]
    }

    fun getRealItemCount(): Int {
       var count = 0
        for (i in 0 until itemCount) {
            if (!getItem(i).isItemDecorator()) {
                count += 1
            }
        }
        return count
    }

}