package com.jdc.restaurant.api.service

import com.jdc.restaurant.api.ClientFactory
import com.jdc.restaurant.api.client.VoucherClient
import com.jdc.restaurant.api.dto.Voucher
import java.util.*

class VoucherService {

    private val client = ClientFactory.generate(VoucherClient::class.java)

    suspend fun create(table:String):Voucher? {
        val voucher = Voucher(tableNumber = table, saleDate = Date())
        return client.create(voucher).execute().body()
    }

    suspend fun findActives() = client.findActives().execute().body() ?: listOf()

    suspend fun findByTable(table:Int) = client.findForTable(table).execute().body() ?: listOf()
}