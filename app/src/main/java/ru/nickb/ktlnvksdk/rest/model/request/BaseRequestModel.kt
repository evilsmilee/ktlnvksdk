package ru.nickb.ktlnvksdk.rest.model.request

import com.google.gson.annotations.SerializedName
import com.vk.sdk.api.VKApiConst
import ru.nickb.ktlnvksdk.CurrentUser
import ru.nickb.ktlnvksdk.consts.ApiConstants
import java.util.*

abstract class BaseRequestModel {

    @SerializedName(VKApiConst.VERSION)
    private var version: Double? = ApiConstants.DEFAULT_VERSION


    @SerializedName(VKApiConst.ACCESS_TOKEN)
    var accessToken = CurrentUser.getAccessToken()


    fun toMap(): MutableMap<String?, String?> {
        val map = HashMap<String?, String?>()
        map[VKApiConst.VERSION] = version.toString()
        if (accessToken != null) {
            map[VKApiConst.ACCESS_TOKEN] = accessToken!!
        }

        onMapCreate(map)

        return map
    }

    abstract fun onMapCreate(map: MutableMap<String?, String?>)
}