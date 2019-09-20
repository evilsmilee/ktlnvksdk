package ru.nickb.ktlnvksdk.rest.model.request

import com.google.gson.annotations.SerializedName
import com.vk.sdk.api.VKApiConst
import ru.nickb.ktlnvksdk.const.ApiConstants

class WallGetRequestModel: BaseRequestModel {


    @SerializedName(VKApiConst.OWNER_ID)
    val ownerId = Int

    @SerializedName(VKApiConst.COUNT)
    val count: Int = ApiConstants.DEFAULT_COUNT

    @SerializedName(VKApiConst.OFFSET)
    val offset: Int = 0

    @SerializedName(VKApiConst.EXTENDED)
    val extended: Int = 1

    constructor(ownerId: Int, count: Int, offset: Int)

    constructor(ownerId: Int)


    override fun onMapCreate(map: MutableMap<String, String>) {
        map[VKApiConst.OWNER_ID] = ownerId.toString()
        map[VKApiConst.COUNT] = count.toString()
        map[VKApiConst.OFFSET] = offset.toString()
        map[VKApiConst.EXTENDED] = extended.toString()
    }
}