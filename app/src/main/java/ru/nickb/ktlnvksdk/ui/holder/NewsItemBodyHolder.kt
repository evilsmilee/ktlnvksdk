package ru.nickb.ktlnvksdk.ui.holder

import android.graphics.Typeface
import android.view.View
import android.widget.TextView
import butterknife.BindView
import butterknife.ButterKnife
import ru.nickb.ktlnvksdk.MyApplication
import ru.nickb.ktlnvksdk.R
import ru.nickb.ktlnvksdk.model.view.NewsItemBodyViewModel
import javax.inject.Inject





class NewsItemBodyHolder(itemView: View) : BaseViewHolder<NewsItemBodyViewModel>(itemView) {

    @BindView(R.id.tv_text)
    var tvText: TextView

    @BindView(R.id.tv_attachments)
    var tvAttachments: TextView

    @Inject
     lateinit var mFontGoogle: Typeface

    init {
        MyApplication.sApplicationComponent.inject(this)
        ButterKnife.bind(this, itemView)
        tvText = itemView.findViewById(R.id.tv_text) as TextView
        tvAttachments = itemView.findViewById(R.id.tv_attachments) as TextView

        tvAttachments.typeface = mFontGoogle
    }

    override fun bindViewHolder(item: NewsItemBodyViewModel) {
        tvText.text = item.text
        tvAttachments.text = item.getmAttachmentString()
    }

    override fun unbindViewHolder() {
        tvText.text = null
        tvAttachments.text = null
    }
}