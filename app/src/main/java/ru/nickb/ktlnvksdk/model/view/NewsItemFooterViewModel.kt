package ru.nickb.ktlnvksdk.model.view

import android.view.View
import ru.nickb.ktlnvksdk.model.WallItem
import ru.nickb.ktlnvksdk.model.view.counter.CommentCounterViewModel
import ru.nickb.ktlnvksdk.model.view.counter.LikeCounterViewModel
import ru.nickb.ktlnvksdk.model.view.counter.RepostCounterViewModel
import ru.nickb.ktlnvksdk.ui.holder.BaseViewHolder
import ru.nickb.ktlnvksdk.ui.holder.NewsItemFooterHolder


class NewsItemFooterViewModel(wallItem: WallItem) : BaseViewModel() {

    var id: Int? = null
    var ownerId: Int = 0
    var dateLong: Long = 0

    var likes: LikeCounterViewModel? = null
    var comments: CommentCounterViewModel? = null
    var reposts: RepostCounterViewModel? = null
        private set


    override val type: LayoutTypes
        = LayoutTypes.NewsFeedItemFooter

    init {
        this.id = wallItem.id
        this.ownerId = wallItem.ownerId!!
        this.dateLong = wallItem.date!!.toLong()
        this.likes = LikeCounterViewModel(wallItem.likes!!)
        this.comments = CommentCounterViewModel(wallItem.comments!!)
        this.reposts = RepostCounterViewModel(wallItem.reposts!!)
    }

    override fun onCreateViewHolder(view: View): BaseViewHolder<*> {
       return NewsItemFooterHolder(view)
    }

    override fun isItemDecorator(): Boolean {
        return true
    }

}