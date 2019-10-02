package ru.nickb.ktlnvksdk.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import io.realm.RealmObject

open class Views: RealmObject() {

    @SerializedName("count")
    @Expose
    var count: Int? = null

}
