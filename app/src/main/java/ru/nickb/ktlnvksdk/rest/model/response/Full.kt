package ru.nickb.ktlnvksdk.rest.model.response

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


data class Full<T>(
    @SerializedName("response")
    @Expose
    var response: T
)