package com.jdc.restaurant.api.utils

import com.fasterxml.jackson.core.JsonGenerator
import com.fasterxml.jackson.databind.JsonSerializer
import com.fasterxml.jackson.databind.SerializerProvider
import java.util.*

class DateSerializer: JsonSerializer<Date>() {

    override fun serialize(value: Date?, gen: JsonGenerator?, serializers: SerializerProvider?) {
        if(null != value && null != gen) {
            gen.writeString(dateFormat.format(value))
        }
    }
}