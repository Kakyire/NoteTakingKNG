package com.kakyiretechnologies.notetakingkng.domain.utils

import android.text.format.DateUtils
import java.text.SimpleDateFormat
import java.util.*


fun String.formatDate(): String {
    return convertDateToLong(this).formatDate().daysPast()
}
fun Long.formatDate(outputDateFormat: String = MODIFIED_DATE): String {

    SimpleDateFormat(
        outputDateFormat,
        Locale.getDefault()
    )
        .format(this)


    return SimpleDateFormat(
        outputDateFormat,
        Locale.getDefault()
    )
        .format(this)


}


fun convertDateToLong(time: String): Long {
    val timeFormat = SimpleDateFormat(DEFAULT_INPUT_DATE_FORMAT)
    val calendar = Calendar.getInstance()
    calendar.time = timeFormat.parse(time)
    return timeFormat.parse(timeFormat.format(calendar.time)).time
}

val String.timeMoment: String
    get() {
        return try {
            val simpleDateFormat = SimpleDateFormat(MODIFIED_DATE, Locale.getDefault())
            val time = simpleDateFormat.parse(this)?.time!!
            val now = System.currentTimeMillis()
            val timeAgo = DateUtils.getRelativeTimeSpanString(
                time,
                now,
                DateUtils.SECOND_IN_MILLIS
            ).toString()

            timeAgo
        } catch (e: Exception) {
            e.stackTrace.toString()
            e.toString()

        }
    }

fun String.daysPast(): String {
    val simpleDateFormat = SimpleDateFormat(MODIFIED_DATE, Locale.getDefault())
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