package ru.nickb.ktlnvksdk.ui.holder

import android.content.Context
import android.content.res.Resources
import android.graphics.Typeface
import android.view.View
import android.widget.TextView
import butterknife.BindView
import butterknife.ButterKnife
import ru.nickb.ktlnvksdk.MyApplication
import ru.nickb.ktlnvksdk.R
import ru.nickb.ktlnvksdk.common.utils.Utils
import ru.nickb.ktlnvksdk.model.view.NewsItemFooterViewModel
import ru.nickb.ktlnvksdk.model.view.counter.CommentCounterViewModel
import ru.nickb.ktlnvksdk.model.view.counter.LikeCounterViewModel
import ru.nickb.ktlnvksdk.model.view.counter.RepostCounterViewModel
import javax.inject.Inject




class NewsItemFooterHolder(itemView: View) : BaseViewHolder<NewsItemFooterViewModel>(itemView) {

    @BindView(R.id.tv_date)
    lateinit var tvDate: TextView

    @BindView(R.id.tv_likes_count)
    lateinit var tvLikesCount: TextView
    @BindView(R.id.tv_likes_icon)
    lateinit var tvLikesIcon: TextView

    @BindView(R.id.tv_comments_icon)
    lateinit var tvCommentIcon: TextView
    @BindView(R.id.tv_comments_countt)
    lateinit var tvCommentsCount: TextView

    @BindView(R.id.tv_reposts_icon)
    lateinit var tvRepostIcon: TextView
    @BindView(R.id.tv_reposts_count)
    lateinit var tvRepostsCount: TextView

    @Inject
    lateinit var mGoogleFontTypeface: Typeface

    private val mResources: Resources
    private val mContext: Context

    init {
        ButterKnife.bind(this, itemView)
        MyApplication.sApplicationComponent.inject(this)

        mContext = itemView.context
        mResources = mContext.resources



        tvLikesIcon.typeface = mGoogleFontTypeface
        tvCommentIcon.typeface = mGoogleFontTypeface
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
        tvCommentIcon.setTextColor(mResources.getColor(comments.iconColor))

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