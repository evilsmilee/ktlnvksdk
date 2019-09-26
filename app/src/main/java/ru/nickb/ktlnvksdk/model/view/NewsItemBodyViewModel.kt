package ru.nickb.ktlnvksdk.model.view

import android.view.View
import ru.nickb.ktlnvksdk.model.WallItem
import ru.nickb.ktlnvksdk.ui.holder.NewsItemBodyHolder

class NewsItemBodyViewModel(wallItem: WallItem) : BaseViewModel() {
    val id: Int = wallItem.id!!

    val text: String? = wallItem.text


    override val type: LayoutTypes
        get() = LayoutTypes.NewsFeedItemBody

    public override fun onCreateViewHolder(view: View): NewsItemBodyHolder {
        return NewsItemBodyHolder(view)
    }
}
