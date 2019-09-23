package ru.nickb.ktlnvksdk.model.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import ru.nickb.ktlnvksdk.R
import ru.nickb.ktlnvksdk.ui.holder.BaseViewHolder

abstract class BaseViewModel {

    abstract fun getType(): LayoutTypes

    fun createViewHolder(parent: ViewGroup): BaseViewHolder<BaseViewModel> {
            return onCreateViewHolder(LayoutInflater.from(parent.context).inflate(getType().getValue(), parent, false))
    }

    protected abstract fun onCreateViewHolder(view: View): BaseViewHolder<BaseViewModel>

    enum class LayoutTypes {
        NewsFeedItemHeader(R.layout.item_news_header),
        NewsFeedItemBody(R.layout.item_news_body),
        NewsFeedItemFooter(R.layout.item_news_footer);

        var id: Int = 0

        constructor(resId: Int) {
            id = resId
        }

        @LayoutRes
        fun getValue(): Int {
            return id
        }
    }
}