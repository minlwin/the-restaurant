package com.jdc.restaurant.api.service

import com.jdc.restaurant.api.ClientFactory
import com.jdc.restaurant.api.client.TablesClient
import com.jdc.restaurant.api.dto.Table

class TablesService {

    private val client = ClientFactory.generate(TablesClient::class.java)

    suspend fun getAll():List<Table> {
        return client.findAll().execute().body() ?: listOf()
    }

    suspend fun findById(id:Int) = client.findById(id).execute().body()
}