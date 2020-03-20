package com.jdc.restaurant.api.client

import com.jdc.restaurant.api.dto.Login
import com.jdc.restaurant.api.dto.LoginResult
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface LoginClient {
    @POST("auth/login")
    fun login(@Body login: Login): Call<LoginResult>
}