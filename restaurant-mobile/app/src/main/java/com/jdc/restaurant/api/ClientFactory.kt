package com.jdc.restaurant.api

import com.jdc.restaurant.api.utils.RequestInterceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.jackson.JacksonConverterFactory

object ClientFactory {

    private const val BASE_URL = "http://10.0.2.2:3000/"

    @JvmStatic
    private val CLIENT = OkHttpClient.Builder().addInterceptor(RequestInterceptor)
        .build()

    @JvmStatic
    fun <T> generate(type:Class<T>):T {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(JacksonConverterFactory.create())
            .client(CLIENT)
            .build().create(type)
    }
}