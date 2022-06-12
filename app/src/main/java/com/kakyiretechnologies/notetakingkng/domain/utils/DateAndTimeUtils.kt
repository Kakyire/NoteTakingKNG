package com.kakyiretechnologies.notetakingkng.domain.utils

import android.text.format.DateUtils
import java.text.SimpleDateFormat
import java.util.*


fun String.formatDate(
    outputDateFormat: String = "dd-MMMM-yyyy",
    inputDateFormat: String = "yyyy-MM-dd",
    locale: Locale = Locale.getDefault(),

    ): String {

    val sdfOutput = SimpleDateFormat(outputDateFormat, locale)
    val sdfInput = SimpleDateFormat(inputDateFormat, locale)

    return sdfOutput.format(sdfInput.parse(this)!!)


}

val String.timeMoment: String
    get() {
        val simpleDateFormat = SimpleDateFormat(DEFAULT_INPUT_DATE_FORMAT, Locale.getDefault())
        val time = simpleDateFormat.parse(this)?.time!!
        val now = Calendar.getInstance().timeInMillis

        return try {
            DateUtils.getRelativeTimeSpanString(
                time,
                now,
                DateUtils.MINUTE_IN_MILLIS
            )
                .toString()
        } catch (e: Exception) {
            e.stackTrace.toString()
            e.toString()

        }
    }

fun String.daysPast(): String
    {
        val simpleDateFormat = SimpleDateFormat(DEFAULT_INPUT_DATE_FORMAT, Locale.getDefault())
        val time = simpleDateFormat.parse(this)?.time!!
        val now = System.currentTimeMillis()

        return DateUtils.getRelativeTimeSpanString(
            time,
            now,
            DateUtils.DAY_IN_MILLIS
        )
            .toString()
    }

fun getCurrentDate(dateFormat: String = DEFAULT_INPUT_DATE_FORMAT): String {
    val date = System.currentTimeMillis()

    return SimpleDateFormat(
        dateFormat,
        Locale.getDefault()
    )
        .format(date)

}

fun main() {
    println(getCurrentDate().timeMoment)
}