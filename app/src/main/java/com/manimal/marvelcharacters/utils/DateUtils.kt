package com.manimal.marvelcharacters.utils

import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

object DateUtils {

    const val FORMAT_DATE_DD_MM_YY = "dd/MM/yy"
    const val FORMAT_DATE_COMPLETE_TIMEZONE = "yyyy-MM-dd'T'HH:mm:ss-SSSZ"
    private const val LANGUAGE_TAG_ESP = "es-ES"

    fun getDateWithFormat(date: String, formatDate: String, formatResult: String): String {
        val dateLong = convertStringToLong(date, formatDate)
        val format = SimpleDateFormat(formatResult, Locale.forLanguageTag(LANGUAGE_TAG_ESP))
        return format.format(dateLong)
    }

    private fun convertStringToLong(dateString: String, format: String): Long {
        return try {
            val formatDate = SimpleDateFormat(format, Locale.forLanguageTag(LANGUAGE_TAG_ESP))
            val date = formatDate.parse(dateString)
            date?.time ?: Calendar.getInstance().timeInMillis
        } catch (parseException: ParseException) {
            Calendar.getInstance().timeInMillis
        }
    }
}
