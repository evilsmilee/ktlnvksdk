package ru.nickb.ktlnvksdk.model.view

import android.view.View
import ru.nickb.ktlnvksdk.model.WallItem
import ru.nickb.ktlnvksdk.ui.holder.BaseViewHolder
import ru.nickb.ktlnvksdk.ui.holder.NewsItemBodyHolder

class NewsFeedItemBodyViewModel: BaseViewModel {

     var mId: Int = 0

     var mText: String = ""

    constructor(wallItem: WallItem) {
        mId = wallItem.id
        mText = wallItem.text
    }


    override fun getType(): LayoutTypes {
        return LayoutTypes.NewsFeedItemBody
    }

    override fun onCreateViewHolder(view: View): BaseViewHolder<BaseViewModel> {
        return NewsItemBodyHolder(view)
    }
}