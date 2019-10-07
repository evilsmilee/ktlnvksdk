package ru.nickb.ktlnvksdk.rest.model.request

import com.google.gson.annotations.SerializedName
import com.vk.sdk.api.VKApiConst
import ru.nickb.ktlnvksdk.consts.ApiConstants



class BoardGetTopicsRequestModel : BaseRequestModel {
    @SerializedName(VKApiConst.GROUP_ID)
    internal var groupId: Int = 0

    @SerializedName(VKApiConst.COUNT)
    var count = ApiConstants.DEFAULT_COUNT

    @SerializedName(VKApiConst.OFFSET)
    var offset = 0

    constructor(groupId: Int) {
        this.groupId = Math.abs(groupId)
    }

    constructor(groupId: Int, count: Int, offset: Int) {
        this.groupId = Math.abs(groupId)
        this.count = count
        this.offset = offset
    }

    fun getGroupId(): Int {
        return groupId
    }

    fun setGroupId(groupId: Int) {
        this.groupId = Math.abs(groupId)
    }

    override fun onMapCreate(map: MutableMap<String?, String?>) {
        map[VKApiConst.GROUP_ID] = getGroupId().toString()
        map[VKApiConst.COUNT] = count.toString()
        map[VKApiConst.OFFSET] = offset.toString()
    }
}