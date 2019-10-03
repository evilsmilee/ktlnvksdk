package ru.nickb.ktlnvksdk.model.view

import com.google.gson.annotations.SerializedName
import com.vk.sdk.api.VKApiConst
import ru.nickb.ktlnvksdk.consts.ApiConstants
import ru.nickb.ktlnvksdk.rest.model.request.BaseRequestModel


class GroupsGetMembersRequestModel : BaseRequestModel {

    @SerializedName(VKApiConst.GROUP_ID)
    internal var groupId: Int = 0

    @SerializedName(VKApiConst.COUNT)
    var count = ApiConstants.DEFAULT_COUNT

    @SerializedName(VKApiConst.OFFSET)
    var offset: Int = 0

    @SerializedName(VKApiConst.FIELDS)
    var fields = ApiConstants.DEFAULT_MEMBER_FIELDS


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
        map[VKApiConst.FIELDS] = fields
    }
}