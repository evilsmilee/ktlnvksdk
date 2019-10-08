package ru.nickb.ktlnvksdk.rest.model.request

import com.google.gson.annotations.SerializedName
import com.vk.sdk.api.VKApiConst
import ru.nickb.ktlnvksdk.consts.ApiConstants

class GroupsGetByIdRequestModel(group: Int): BaseRequestModel() {

    @SerializedName(VKApiConst.GROUP_ID)
    var groupId: Int = 0

    @SerializedName(VKApiConst.FIELDS)
    var fields = ApiConstants.DEFAULT_GROUP_FIELDS

    init {
        groupId = Math.abs(groupId)
    }

    override fun onMapCreate(map: MutableMap<String?, String?>) {
        map[VKApiConst.GROUP_ID] = groupId.toString()
        map[VKApiConst.FIELDS] = fields
    }
}