package ru.nickb.ktlnvksdk.rest.api

import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.QueryMap
import ru.nickb.ktlnvksdk.model.Profile
import ru.nickb.ktlnvksdk.rest.model.response.Full

interface UsersApi {
    @GET(ApiMethods.USERS_GET)
    fun get(@QueryMap map: MutableMap<String?, String?>): Observable<Full<List<Profile>>>
}