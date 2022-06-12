package com.kakyiretechnologies.notetakingkng.domain.utils

import java.text.SimpleDateFormat
import java.util.*


fun String.formatDate(
    outputDateFormat: String = "dd-MMMM-yyyy",
    inputDateFormat: String = "yyyy-MM-dd",
    locale: Locale = Locale.getDefault(),

    ): String {

    val sdfOutput = SimpleDateFormat(outputDateFormat, locale)
    val sdfInput = SimpleDateFormat(inputDateFormat, locale)

    return sdfOutput.format(sdfInput.parse(this))


}

fun getCurrentDate(): String {
    val date = System.currentTimeMillis()

    return SimpleDateFormat(
        "MMM d, YYYY",
        Locale.getDefault()
    )
        .format(date)

}