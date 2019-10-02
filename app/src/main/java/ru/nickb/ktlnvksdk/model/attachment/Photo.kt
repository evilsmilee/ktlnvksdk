package ru.nickb.ktlnvksdk.model.attachment

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.vk.sdk.api.model.VKAttachments
import io.realm.RealmObject


open class Photo : Attachment, RealmObject() {

    @SerializedName("id")
    @Expose
    private var id: Int? = null
    @SerializedName("album_id")
    @Expose
    var albumId: Int? = null
    @SerializedName("owner_id")
    @Expose
    var ownerId: Int? = null
    @SerializedName("user_id")
    @Expose
    var userId: Int? = null
    @SerializedName("photo_75")
    @Expose
    var photo75: String? = null
    @SerializedName("photo_130")
    @Expose
    var photo130: String? = null
    @SerializedName("photo_604")
    @Expose
    var photo604: String? = null
    @SerializedName("photo_807")
    @Expose
    var photo807: String? = null
    @SerializedName("photo_1280")
    @Expose
    var photo1280: String? = null
    @SerializedName("width")
    @Expose
    var width: Int? = null
    @SerializedName("height")
    @Expose
    var height: Int? = null
    @SerializedName("text")
    @Expose
    var text: String? = null
    @SerializedName("date")
    @Expose
    var date: Int? = null
    @SerializedName("access_key")
    @Expose
    var accessKey: String? = null

    override fun getType(): String {
        return VKAttachments.TYPE_PHOTO
    }

    override fun getId(): Int {
        return id!!
    }

    fun setId(id: Int?) {
        this.id = id
    }
}