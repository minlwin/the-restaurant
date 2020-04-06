package com.jdc.restaurant.ui.model

import androidx.lifecycle.*
import com.jdc.restaurant.api.dto.Voucher
import com.jdc.restaurant.api.service.VoucherService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class VouchersModel : ViewModel() {

    private val service = VoucherService()

    val tableNumber = MutableLiveData(0)

    val showFab = tableNumber.map { it != 0 }

    val vouchers = tableNumber.map {
        val result = mutableListOf<Voucher>()

        viewModelScope.launch {
            val list = withContext(Dispatchers.IO) {
                if(it == 0)
                    service.findActives()
                else
                    service.findByTable(it)
            }

            result.addAll(list)
        }

        result
    }

    fun createVoucher() {

    }

}