package ru.nickb.ktlnvksdk.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import io.realm.RealmObject

open class Comments: RealmObject() {

    @SerializedName("count")
    @Expose
    var count: Int? = null
    @SerializedName("can_post")
    @Expose
    var canPost: Int? = null

}
