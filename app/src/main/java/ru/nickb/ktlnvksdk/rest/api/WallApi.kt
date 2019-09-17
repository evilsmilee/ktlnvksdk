package ru.nickb.ktlnvksdk.rest.api

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query
import ru.nickb.ktlnvksdk.rest.model.response.BaseItemResponse
import ru.nickb.ktlnvksdk.rest.model.response.Full


interface WallApi {
    @GET(ApiMethods.WALL_GET)
    fun get(@Query("owner_id") ownerId: String,
            @Query("access_token") accessToken: String,
            @Query("extended") extended: Int,
            @Query("v") version: String): Call<Full<BaseItemResponse<*>>>
}



