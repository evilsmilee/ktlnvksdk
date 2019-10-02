package ru.nickb.ktlnvksdk.rest.api

import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.QueryMap
import ru.nickb.ktlnvksdk.rest.model.response.WallGetResponse

interface WallApi {
    @GET(ApiMethods.WALL_GET)
    operator fun get(@QueryMap map: Map<String?, String?>): Observable<WallGetResponse>
}