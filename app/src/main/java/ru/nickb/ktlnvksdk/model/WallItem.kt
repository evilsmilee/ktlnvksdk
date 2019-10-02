package ru.nickb.ktlnvksdk.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import io.realm.RealmList
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import ru.nickb.ktlnvksdk.model.attachment.ApiAttachment


open class WallItem: RealmObject() {

    var senderName: String = ""
    var senderPhoto: String = ""
    var attachmentsString: String = ""

    @SerializedName("id")
    @Expose
    @PrimaryKey
    var id: Int? = null
    @SerializedName("from_id")
    @Expose
    var fromId: Int? = null
    @SerializedName("owner_id")
    @Expose
    var ownerId: Int? = null
    @SerializedName("date")
    @Expose
    var date: Int? = null
    @SerializedName("marked_as_ads")
    @Expose
    var markedAsAds: Int? = null
    @SerializedName("post_type")
    @Expose
    var postType: String? = null
    @SerializedName("text")
    @Expose
    var text: String? = null
    @SerializedName("can_pin")
    @Expose
    var canPin: Int? = null
    @SerializedName("attachments")
    @Expose
    var attachments: RealmList<ApiAttachment> = RealmList()



    @SerializedName("copy_history")
    @Expose
    private var copyHistory: RealmList<WallItem> = RealmList()

    @SerializedName("post_source")
    @Expose
    var postSource: PostSource? = null
    @SerializedName("comments")
    @Expose
    var comments: Comments? = null
    @SerializedName("likes")
    @Expose
    var likes: Likes? = null
    @SerializedName("reposts")
    @Expose
    var reposts: Reposts? = null
    @SerializedName("views")
    @Expose
    var views: Views? = null



    fun haveSharedRepost(): Boolean {
        return copyHistory.isNotEmpty()
    }

    fun getSharedRepost(): WallItem? {
        if (haveSharedRepost()) {
            return copyHistory[0]
        }
        return null
    }

}
