package ru.nickb.ktlnvksdk.model.attachment

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import io.realm.RealmObject


open class Page : Attachment, RealmObject() {

    @SerializedName("id")
    @Expose
    var id: Int? = null

    @SerializedName("title")
    @Expose
    var title: String? = null

    @SerializedName("view_url")
    @Expose
    var url: String? = null

    override fun getType(): String {
        return "page"
    }

    override fun getId(): Int {
        return id!!
    }
}