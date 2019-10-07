package ru.nickb.ktlnvksdk.rest.api

import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.QueryMap
import ru.nickb.ktlnvksdk.model.Topic
import ru.nickb.ktlnvksdk.rest.model.response.BaseItemResponse
import ru.nickb.ktlnvksdk.rest.model.response.Full

interface BoardApi {

    @GET(ApiMethods.BOARD_GET_TOPICS)
    fun getTopics(@QueryMap map:  MutableMap<String?, String?>): Observable<Full<BaseItemResponse<Topic>>>

}