package ru.nickb.ktlnvksdk.ui.holder

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import ru.nickb.ktlnvksdk.model.view.BaseViewModel

abstract class BaseViewHolder<Item : BaseViewModel>(itemView: View) :
    RecyclerView.ViewHolder(itemView) {

    abstract fun bindViewHolder(item: Item)

    abstract fun unbindViewHolder()
}
