package ru.nickb.ktlnvksdk.model.attachment

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.vk.sdk.api.model.VKAttachments
import ru.nickb.ktlnvksdk.model.Attachment


class Link : Attachment {


    @SerializedName("url")
    @Expose
    var url: String? = null
    @SerializedName("title")
    @Expose
    var title: String? = null

    @SerializedName("name")
    @Expose
    var name: String? = null

    @SerializedName("caption")
    @Expose
    var caption: String? = null
    @SerializedName("description")
    @Expose
    var description: String? = null
    @SerializedName("photo")
    @Expose
    var photo: Photo? = null
    @SerializedName("is_external")
    @Expose
    var isExternal: Int = 0

    override fun getType(): String {
        return VKAttachments.TYPE_LINK
    }

    override fun getId(): Int {
        return id!!
    }
}