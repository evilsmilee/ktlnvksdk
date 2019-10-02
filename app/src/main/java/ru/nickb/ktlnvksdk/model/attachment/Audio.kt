package ru.nickb.ktlnvksdk.model.attachment

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.vk.sdk.api.model.VKAttachments
import io.realm.RealmObject


open class Audio : Attachment, RealmObject() {


    @SerializedName("id")
    @Expose
    var id: Int? = null
    @SerializedName("owner_id")
    @Expose
    var ownerId: Int = 0
    @SerializedName("artist")
    @Expose
    var artist: String? = null
    @SerializedName("title")
    @Expose
    var title: String? = null
    @SerializedName("duration")
    @Expose
    var duration: Int = 0
    @SerializedName("date")
    @Expose
    var date: Int = 0
    @SerializedName("url")
    @Expose
    var url: String? = null
    @SerializedName("lyrics_id")
    @Expose
    var lyricsId: Int = 0
    @SerializedName("genre_id")
    @Expose
    var genreId: Int = 0

    override fun getType(): String {
       return VKAttachments.TYPE_AUDIO
    }

    override fun getId(): Int {
        return id!!
    }
}