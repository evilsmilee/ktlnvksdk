package ru.nickb.ktlnvksdk.model.view

import android.view.View
import ru.nickb.ktlnvksdk.model.WallItem
import ru.nickb.ktlnvksdk.ui.holder.BaseViewHolder
import ru.nickb.ktlnvksdk.ui.holder.NewsItemHeaderHolder

class NewsItemHeaderViewModel(wallItem: WallItem) : BaseViewModel() {

    private var mId:Int? = null
    private var mProfilePhoto: String = ""
    private var mProfileName: String = ""
    private var mIsRepost: Boolean
    private var mRepostProfileName: String = ""

    init {
        mId = wallItem.id
        mProfilePhoto = wallItem.senderPhoto
        mProfileName = wallItem.senderName
        mIsRepost = wallItem.haveSharedRepost()
        if (mIsRepost) {
            mRepostProfileName = wallItem.getSharedRepost()!!.senderName
        }
    }

    override val type: LayoutTypes
       = LayoutTypes.NewsFeedItemHeader

    override fun onCreateViewHolder(view: View): BaseViewHolder<*> {
        return NewsItemHeaderHolder(view)
    }

    fun getProfilePhoto(): String {
        return mProfilePhoto
    }

    fun getProfileName(): String {
        return mProfileName
    }

    fun isRepost(): Boolean {
        return mIsRepost
    }
}