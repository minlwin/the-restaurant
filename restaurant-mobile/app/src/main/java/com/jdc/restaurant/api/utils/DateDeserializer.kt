package com.jdc.restaurant.api.utils

import com.fasterxml.jackson.core.JsonParser
import com.fasterxml.jackson.core.JsonTokenId
import com.fasterxml.jackson.databind.DeserializationContext
import com.fasterxml.jackson.databind.JsonDeserializer
import java.util.*

class DateDeserializer : JsonDeserializer<Date>() {

    override fun deserialize(p: JsonParser?, ctxt: DeserializationContext?): Date? {
        if(null != p && null != ctxt) {
            if(p.currentTokenId == JsonTokenId.ID_STRING) {
                return dateFormat.parse(p.text)
            }
        }

        return null
    }
}