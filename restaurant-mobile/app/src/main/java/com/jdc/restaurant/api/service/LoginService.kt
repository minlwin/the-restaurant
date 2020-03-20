package com.jdc.restaurant.api.service

import com.jdc.restaurant.api.ClientContext
import com.jdc.restaurant.api.ClientFactory
import com.jdc.restaurant.api.client.LoginClient
import com.jdc.restaurant.api.dto.Login

class LoginService {

    private val client = ClientFactory.generate(LoginClient::class.java)

    suspend fun login(userName:String, password:String):Boolean {

        try {
            client.login(Login(userName, password)).execute().body()?.also {
                ClientContext.loginUser = it.user
                ClientContext.token = it.token
            }

            return true
        } catch (t:Throwable) {
            t.printStackTrace()
        }
        return false
    }
}