package ru.nickb.ktlnvksdk.rest.model.request

import com.google.gson.annotations.SerializedName
import com.vk.sdk.api.VKApiConst
import ru.nickb.ktlnvksdk.consts.ApiConstants

class UsersGetRequestModel(@field:SerializedName(VKApiConst.USER_IDS) var userIds: String) : BaseRequestModel() {

    @SerializedName(VKApiConst.FIELDS)
    var fields = ApiConstants.DEFAULT_USER_FIELDS

    override fun onMapCreate(map: MutableMap<String?, String?>) {

        map[VKApiConst.USER_ID] = userIds
        map[VKApiConst.FIELDS] = fields

    }
}