package com.eldiro.movieskotlin.services

import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.google.gson.JsonParseException
import java.lang.reflect.Type
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

class DateDeserializer : JsonDeserializer<Date>{
    private val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())

    @Throws(JsonParseException::class)
    override fun deserialize(json: JsonElement, typeOfT: Type?, context: JsonDeserializationContext?): Date {
        val dateAsString = json.asString
        return try {
            dateFormat.parse(dateAsString)
        } catch (e: ParseException) {
            throw JsonParseException(e)
        }
    }
}