package ru.nickb.ktlnvksdk.ui.holder

import android.view.View
import android.widget.TextView
import kotlinx.android.synthetic.main.item_news_body.view.*
import ru.nickb.ktlnvksdk.R
import ru.nickb.ktlnvksdk.model.view.NewsFeedItemBody

class NewsItemBodyHolder: BaseViewHolder<NewsFeedItemBody> {

    private val mText: TextView

    constructor(itemView: View) : super(itemView) {
        mText = itemView.findViewById(R.id.tv_text)
    }

    override fun bindViewHolder(item: NewsFeedItemBody) {
        mText.text = item.mText
    }

    override fun unbindViewHolder() {
        mText.text = null
    }
}