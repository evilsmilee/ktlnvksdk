package ru.nickb.ktlnvksdk.rest.model.response

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


 open class Full<T> {
     @SerializedName("response")
     @Expose
     var response: T? = null
 }

