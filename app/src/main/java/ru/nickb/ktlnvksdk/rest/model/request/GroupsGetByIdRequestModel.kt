package ru.nickb.ktlnvksdk.rest.model.request

import com.google.gson.annotations.SerializedName
import com.vk.sdk.api.VKApiConst
import ru.nickb.ktlnvksdk.consts.ApiConstants

class GroupsGetByIdRequestModel(groupid: Int) : BaseRequestModel() {

    @SerializedName(VKApiConst.GROUP_ID)
    internal var groupid: Int = 0

    @SerializedName(VKApiConst.FIELDS)
    var fields = ApiConstants.DEFAULT_GROUP_FIELDS

    init {
        this.groupid = Math.abs(groupid)
    }

    fun getGroupid(): Int {
        return groupid
    }

    fun setGroupid(groupid: Int) {
        this.groupid = Math.abs(groupid)
    }

   override fun onMapCreate(map: MutableMap<String?, String?>) {
        map[VKApiConst.GROUP_ID] = getGroupid().toString()
        map[VKApiConst.FIELDS] = fields

    }
}