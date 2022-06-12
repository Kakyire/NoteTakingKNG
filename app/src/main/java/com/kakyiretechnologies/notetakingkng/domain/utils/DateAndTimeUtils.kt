package com.kakyiretechnologies.notetakingkng.domain.utils

import android.text.format.DateUtils
import java.text.SimpleDateFormat
import java.util.*


fun String.formatDate(
    outputDateFormat: String = DEFAULT_INPUT_DATE_FORMAT,
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

fun String.daysPast(): String {
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

fun timeAgo(date:String): String {
    val simpleDateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.getDefault())

    val formatted =simpleDateFormat.format(date)
    val time = simpleDateFormat.parse(formatted).time
    val now = Calendar.getInstance().timeInMillis
    val timeAgo = DateUtils.getRelativeTimeSpanString(
        time,
        now,
        DateUtils.SECOND_IN_MILLIS
    )
        .toString()
    return timeAgo
}
fun getCurrentDate(dateFormat: String = DEFAULT_INPUT_DATE_FORMAT): String {
    val date = Date().time

    return SimpleDateFormat(
        dateFormat,
        Locale.getDefault()
    )
        .format(date)

}

fun main() {
    val date = System.currentTimeMillis()
    val formatted = SimpleDateFormat("dd MMMM, yy HH:mm", Locale.getDefault())
    val dateValue = formatted.format(date)



    println(dateValue)
}