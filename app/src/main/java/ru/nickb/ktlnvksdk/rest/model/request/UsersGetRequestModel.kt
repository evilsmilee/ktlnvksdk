package ru.nickb.ktlnvksdk.rest.model.request

import com.google.gson.annotations.SerializedName
import com.vk.sdk.api.VKApiConst
import ru.nickb.ktlnvksdk.consts.ApiConstants

class UsersGetRequestModel(userId: String?): BaseRequestModel(){

    @SerializedName(VKApiConst.USER_IDS)
    val userId: String? = null

    @SerializedName(VKApiConst.FIELDS)
    val fields = ApiConstants.DEFAULT_USERS_FIELDS


    override fun onMapCreate(map: MutableMap<String?, String?>) {
        map[VKApiConst.USER_ID] = userId
        map[VKApiConst.FIELDS] = fields
    }
}