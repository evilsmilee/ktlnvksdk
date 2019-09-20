package ru.nickb.ktlnvksdk.rest.model.request

import com.google.gson.annotations.SerializedName
import com.vk.sdk.api.VKApiConst
import ru.nickb.ktlnvksdk.CurrentUser
import ru.nickb.ktlnvksdk.const.ApiConstants

abstract class BaseRequestModel {

    @SerializedName(VKApiConst.VERSION)
    val version: Double = ApiConstants.DEFAULT_VERSION

    @SerializedName(VKApiConst.ACCESS_TOKEN)
    val accessToken: String = CurrentUser.accessToken.toString()

    fun toMap(): MutableMap<String, String> {
        val map: MutableMap<String, String> = HashMap()
        map[VKApiConst.VERSION] = version.toString()
        map[VKApiConst.ACCESS_TOKEN] = accessToken
        onMapCreate(map)

        return map
    }

    abstract fun onMapCreate(map: MutableMap<String, String>)
}