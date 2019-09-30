package ru.nickb.ktlnvksdk.ui.holder

import android.content.Context
import android.content.res.Resources
import android.graphics.Typeface
import android.view.View
import android.widget.TextView
import ru.nickb.ktlnvksdk.MyApplication
import ru.nickb.ktlnvksdk.R
import ru.nickb.ktlnvksdk.common.utils.Utils
import ru.nickb.ktlnvksdk.model.view.NewsItemFooterViewModel
import ru.nickb.ktlnvksdk.model.view.counter.CommentCounterViewModel
import ru.nickb.ktlnvksdk.model.view.counter.LikeCounterViewModel
import ru.nickb.ktlnvksdk.model.view.counter.RepostCounterViewModel
import javax.inject.Inject


class NewsItemFooterHolder(itemView: View) : BaseViewHolder<NewsItemFooterViewModel>(itemView) {

    private val tvDate: TextView

    private val tvLikesCount: TextView
    private val tvLikesIcon: TextView
    private val tvCommentsIcon: TextView
    private val tvCommentsCount: TextView
    private val tvRepostIcon: TextView
    private val tvRepostsCount: TextView

    @Inject
    lateinit var mGoogleFontTypeface: Typeface

    private val mResources: Resources
    private val mContext: Context

    init {
        MyApplication.sApplicationComponent.inject(this)

        mContext = itemView.context
        mResources = mContext.resources

        tvDate = itemView.findViewById(R.id.tv_date)
        tvLikesIcon = itemView.findViewById(R.id.tv_likes_icon)
        tvLikesCount = itemView.findViewById(R.id.tv_likes_count)
        tvCommentsIcon = itemView.findViewById(R.id.tv_comments_icon)
        tvCommentsCount = itemView.findViewById(R.id.tv_comments_count)
        tvRepostIcon = itemView.findViewById(R.id.tv_reposts_icon)
        tvRepostsCount = itemView.findViewById(R.id.tv_reposts_count)

        tvLikesIcon.typeface = mGoogleFontTypeface
        tvCommentsIcon.typeface = mGoogleFontTypeface
        tvRepostIcon.typeface = mGoogleFontTypeface

    }


    override fun bindViewHolder(item: NewsItemFooterViewModel) {
        tvDate.text = Utils.parseDate(item.dateLong, mContext)

        bindLikes(item.likes!!)
        bindComments(item.comments!!)
        bindReposts(item.reposts!!)

    }

    private fun bindLikes(likes: LikeCounterViewModel) {
        tvLikesCount.text = likes.count.toString()
        tvLikesCount.setTextColor(mResources.getColor(likes.textColor))
        tvLikesIcon.setTextColor(mResources.getColor(likes.iconColor))

    }

    private fun bindComments(comments: CommentCounterViewModel) {
        tvCommentsCount.text = comments.count.toString()
        tvCommentsCount.setTextColor(mResources.getColor(comments.textColor))
        tvCommentsIcon.setTextColor(mResources.getColor(comments.iconColor))

    }

    private fun bindReposts(reposts: RepostCounterViewModel) {
        tvRepostsCount.text = reposts.count.toString()
        tvRepostsCount.setTextColor(mResources.getColor(reposts.textColor))
        tvRepostIcon.setTextColor(mResources.getColor(reposts.iconColor))

    }


    override fun unbindViewHolder() {

        tvDate.text = null
        tvLikesCount.text = null
        tvCommentsCount.text = null
        tvRepostsCount.text = null

    }
}