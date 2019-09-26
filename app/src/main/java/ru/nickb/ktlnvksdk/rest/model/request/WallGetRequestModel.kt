package ru.nickb.ktlnvksdk.rest.model.request

import com.google.gson.annotations.SerializedName
import com.vk.sdk.api.VKApiConst
import ru.nickb.ktlnvksdk.consts.ApiConstants

class WallGetRequestModel : BaseRequestModel {
    @SerializedName(VKApiConst.OWNER_ID)
    var ownerId: Int = 0


    @SerializedName(VKApiConst.COUNT)
    var count = ApiConstants.DEFAULT_COUNT

    @SerializedName(VKApiConst.OFFSET)
    var offset = 0

    @SerializedName(VKApiConst.EXTENDED)
    var extended = 1

    constructor(ownerId: Int) {
        this.ownerId = ownerId
    }

    constructor(ownerId: Int, count: Int, offset: Int) {
        this.ownerId = ownerId
        this.count = count
        this.offset = offset
    }


    override fun onMapCreate(map: MutableMap<String, String>) {
        map.put(VKApiConst.OWNER_ID, ownerId.toString())
        map.put(VKApiConst.COUNT, count.toString())
        map.put(VKApiConst.OFFSET, offset.toString())
        map.put(VKApiConst.EXTENDED, extended.toString())
    }
}