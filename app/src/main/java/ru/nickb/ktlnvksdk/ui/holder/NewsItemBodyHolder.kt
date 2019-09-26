package ru.nickb.ktlnvksdk.ui.holder

import android.view.View
import android.widget.TextView
import ru.nickb.ktlnvksdk.R
import ru.nickb.ktlnvksdk.model.view.NewsItemBodyViewModel

class NewsItemBodyHolder(itemView: View) : BaseViewHolder<NewsItemBodyViewModel>(itemView) {

    var tvText: TextView = itemView.findViewById(R.id.tv_text)


    override fun bindViewHolder(item: NewsItemBodyViewModel) {
        tvText.text = item.text
    }

    override fun unbindViewHolder() {
        tvText.text = null
    }
}