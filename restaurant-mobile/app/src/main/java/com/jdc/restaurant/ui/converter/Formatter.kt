package com.jdc.restaurant.ui.converter

import java.text.DecimalFormat
import java.text.SimpleDateFormat
import java.util.*

object Formatter {

    @JvmStatic
    private val format = SimpleDateFormat("MM/dd/yyyy HH:mm")

    @JvmStatic
    private val decimalFormat = DecimalFormat("#,##0 MMK")

    @JvmStatic
    fun formatDate(date:Date?) =  date?.let { format.format(it) }

    @JvmStatic
    fun formatNumber(data:Int?) = data?.let { decimalFormat.format(it) }
}