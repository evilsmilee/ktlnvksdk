package ru.nickb.ktlnvksdk.rest

import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class RestClient {
    private val VK_BASE_URL = "https://api.vk.com/method/"



    private val  mRetrofit =  Retrofit.Builder()
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(VK_BASE_URL)
        .build()


    fun <S> createService(serviceClass: Class<S>): S {
        return mRetrofit.create(serviceClass)
    }

}