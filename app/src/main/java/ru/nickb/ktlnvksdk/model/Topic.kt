package ru.nickb.ktlnvksdk.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.vk.sdk.api.model.Identifiable
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey


open class Topic : RealmObject(), Identifiable {

    @PrimaryKey
    @SerializedName("id")
    @Expose
     var id: Int? = 0

    @SerializedName("title")
    @Expose
    var title: String = ""

    @SerializedName("comments")
    @Expose
     var comments: Int = 0

    var groupid: Int = 0

    override fun getId(): Int {
        return id!!
    }


}