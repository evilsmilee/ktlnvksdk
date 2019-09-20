package ru.nickb.ktlnvksdk.rest

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RestClient {
    private val VK_BASE_URL = "https://api.vk.com/method/"



    private val  mRetrofit =  Retrofit.Builder().
        addConverterFactory(GsonConverterFactory.create())
    .baseUrl(VK_BASE_URL)
    .build()


    fun <S> createService(serviceClass: Class<S>): S {
        return mRetrofit.create(serviceClass)
    }

}