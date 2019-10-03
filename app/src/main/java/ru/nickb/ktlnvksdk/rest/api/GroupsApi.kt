package ru.nickb.ktlnvksdk.rest.api

import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.QueryMap
import ru.nickb.ktlnvksdk.model.Member
import ru.nickb.ktlnvksdk.rest.model.response.BaseItemResponse
import ru.nickb.ktlnvksdk.rest.model.response.Full

interface GroupsApi {

    @GET(ApiMethods.GROUPS_GET_MEMBERS)
    fun getMembers(@QueryMap map: MutableMap<String?, String?>): Observable<Full<BaseItemResponse<Member>>>
}