package ru.nickb.ktlnvksdk.model.view

import android.view.View
import ru.nickb.ktlnvksdk.model.WallItem
import ru.nickb.ktlnvksdk.ui.holder.NewsItemBodyHolder


 class NewsItemBodyViewModel(wallItem: WallItem) : BaseViewModel() {

    var id: Int? = null

    var text: String? = ""

    private var mAttachmentString: String? = null

    private val mIsRepost: Boolean


    override val type: LayoutTypes
        get() = LayoutTypes.NewsFeedItemBody

    init {
        this.id = wallItem.id
        this.mIsRepost = wallItem.haveSharedRepost()

        if (mIsRepost) {
            this.text = wallItem.getSharedRepost()!!.text
            this.mAttachmentString = wallItem.getSharedRepost()!!.attachmentsString
        } else {
            this.text = wallItem.text
            this.mAttachmentString = wallItem.attachmentsString
        }
    }

    public override fun onCreateViewHolder(view: View): NewsItemBodyHolder {
        return NewsItemBodyHolder(view)
    }

    fun getmAttachmentString(): String? {
        return mAttachmentString
    }


     override fun isItemDecorator(): Boolean {
         return true
     }

}

