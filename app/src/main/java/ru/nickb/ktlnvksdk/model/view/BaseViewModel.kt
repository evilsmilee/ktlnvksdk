package ru.nickb.ktlnvksdk.model.view

import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import ru.nickb.ktlnvksdk.R
import ru.nickb.ktlnvksdk.ui.holder.BaseViewHolder


abstract class BaseViewModel {

    abstract fun getType(): LayoutTypes

    fun createViewHolder(parent: ViewGroup) {
        return
    }

    protected abstract fun onCreateViewHolder(view: View): BaseViewHolder<BaseViewModel>

    enum class LayoutTypes(resId: Int){
        NewsFeedItemHeader(R.layout.item_news_header),
        NewsFeedItemBody(R.layout.item_news_body),
        NewsFeedItemFooter(R.layout.item_news_footer);

        val id: Int = 0


        @LayoutRes
        fun getValue(): Int {
            return id
        }

    }
}



