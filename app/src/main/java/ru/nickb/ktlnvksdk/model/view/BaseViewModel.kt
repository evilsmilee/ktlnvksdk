package ru.nickb.ktlnvksdk.model.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import ru.nickb.ktlnvksdk.R
import ru.nickb.ktlnvksdk.ui.holder.BaseViewHolder

abstract class BaseViewModel {

    abstract val type: LayoutTypes

    fun createViewHolder(parent: ViewGroup): BaseViewHolder<out BaseViewModel> {
        return onCreateViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(type.value, parent, false)
        )
    }

    protected abstract fun onCreateViewHolder(view: View): BaseViewHolder<*>


    enum class LayoutTypes(
        @get:LayoutRes
        val value: Int
    ) {
        NewsFeedItemHeader(R.layout.item_news_header),
        NewsFeedItemBody(R.layout.item_news_body),
        NewsFeedItemFooter(R.layout.item_news_footer)
    }

    protected fun isItemDecorator(): Boolean{
        return false
    }
}
