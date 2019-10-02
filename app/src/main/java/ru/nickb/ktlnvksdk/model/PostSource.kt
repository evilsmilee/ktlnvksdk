package ru.nickb.ktlnvksdk.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import io.realm.RealmObject

open class PostSource: RealmObject() {

    @SerializedName("type")
    @Expose
    var type: String? = null

}
