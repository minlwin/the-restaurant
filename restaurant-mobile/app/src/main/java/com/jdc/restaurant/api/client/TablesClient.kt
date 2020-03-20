package com.jdc.restaurant.api.client

import com.jdc.restaurant.api.dto.Table
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface TablesClient {

    @GET("tables")
    fun findAll(): Call<List<Table>>

    @GET("tables/{id}")
    fun findById(@Path("id") id:Int): Call<Table>
}