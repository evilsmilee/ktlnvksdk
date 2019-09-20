package ru.nickb.ktlnvksdk.rest.api

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.QueryMap
import ru.nickb.ktlnvksdk.rest.model.response.WallGetResponse


interface WallApi {
    @GET(ApiMethods.WALL_GET)
    fun get(@QueryMap map: MutableMap<String,String>): Call<WallGetResponse>
}



