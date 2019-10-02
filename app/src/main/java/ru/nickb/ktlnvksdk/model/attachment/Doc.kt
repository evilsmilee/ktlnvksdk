package ru.nickb.ktlnvksdk.model.attachment

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.vk.sdk.api.model.VKAttachments
import io.realm.RealmObject


open class Doc : Attachment, RealmObject() {
    @SerializedName("id")
    @Expose
    var id: Int? = null
    @SerializedName("owner_id")
    @Expose
    var ownerId: Int = 0
    @SerializedName("title")
    @Expose
    var title: String? = null
    @SerializedName("size")
    @Expose
    var size: Int = 0
    @SerializedName("ext")
    @Expose
    var ext: String? = null
    @SerializedName("url")
    @Expose
    var url: String? = null
    @SerializedName("date")
    @Expose
    var date: Int = 0
    @SerializedName("type")
    @Expose
    var mType: Int = 0
        private set
    @SerializedName("access_key")
    @Expose
    var accessKey: String? = null

    override fun getType(): String {
        return VKAttachments.TYPE_DOC
    }

    override fun getId(): Int {
        return id!!
    }
}