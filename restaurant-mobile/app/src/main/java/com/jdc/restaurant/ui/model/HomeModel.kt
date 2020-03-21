package com.jdc.restaurant.ui.model

import android.view.View
import androidx.lifecycle.*
import com.jdc.restaurant.api.dto.Login
import com.jdc.restaurant.api.dto.Table
import com.jdc.restaurant.api.service.LoginService
import com.jdc.restaurant.api.service.TablesService
import com.jdc.restaurant.ui.RestaurantException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class HomeModel : ViewModel() {

    val alreadyLogin = MutableLiveData(false)
    val loginInfo = MutableLiveData(Login())
    val message = MutableLiveData("")

    private val tableService = TablesService()
    private val loginService = LoginService()

    val tables = alreadyLogin.map { if(it) getTables() else listOf() }

    private fun getTables():List<Table> {
        val result = mutableListOf<Table>()

        viewModelScope.launch {
            val list = withContext(Dispatchers.IO) {
                tableService.getAll()
            }
            result.addAll(list)
        }

        return result
    }

    fun login(v:View) {

        try {

            loginInfo.value?.also {
                validate(it)

                viewModelScope.launch {

                    alreadyLogin.value = withContext(Dispatchers.IO) {
                        loginService.login(it)
                    }
                }
            }

        } catch (e:RestaurantException) {
            message.value = e.message
        }
    }

    private fun validate(login:Login) {
        if(login.username.isEmpty()) {
            throw RestaurantException("Please enter login id.")
        }

        if(login.password.isEmpty()) {
            throw RestaurantException("Please enter password.")
        }
    }
}