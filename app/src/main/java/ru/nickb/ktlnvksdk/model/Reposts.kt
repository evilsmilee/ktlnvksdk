package ru.nickb.ktlnvksdk.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Reposts {

    @SerializedName("count")
    @Expose
    var count: Int? = null
    @SerializedName("user_reposted")
    @Expose
    var userReposted: Int? = null

}
