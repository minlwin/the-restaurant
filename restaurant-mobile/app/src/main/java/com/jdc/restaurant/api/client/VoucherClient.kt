package com.jdc.restaurant.api.client

import com.jdc.restaurant.api.dto.Voucher
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface VoucherClient {

    @GET("sales/actives")
    fun findActives():Call<List<Voucher>>

    @GET("sales/actives/{table}")
    fun findForTable(@Path("table") table:Int):Call<List<Voucher>>

    @POST("sales")
    fun create(@Body voucher: Voucher):Call<Voucher>
}