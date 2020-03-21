package com.jdc.restaurant.api.service

import android.util.Log
import com.jdc.restaurant.api.ClientContext
import com.jdc.restaurant.api.ClientFactory
import com.jdc.restaurant.api.client.LoginClient
import com.jdc.restaurant.api.dto.Login

class LoginService {

    private val client = ClientFactory.generate(LoginClient::class.java)

    suspend fun login(login: Login):Boolean {

        val response = client.login(login).execute()

        if(!response.isSuccessful) {
            Log.d("com.jdc.restaurant", response.errorBody()?.string())
            return false
        }

        response.body()?.also {
            ClientContext.loginUser = it.user
            ClientContext.token = it.token
        }

        return true
    }
}