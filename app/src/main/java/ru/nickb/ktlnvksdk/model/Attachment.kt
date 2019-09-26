package ru.nickb.ktlnvksdk.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Attachment {

    @SerializedName("type")
    @Expose
    var type: String? = null
    @SerializedName("photo")
    @Expose
    var photo: Photo? = null

}
