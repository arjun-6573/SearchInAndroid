package com.example.codeinandroid.utils

import java.text.SimpleDateFormat
import java.util.*

fun fromUTCToDate(dateInUTC: String): Date {
    return try {
        val sdf = SimpleDateFormat(AppConstant.UTC_DATE_TIME_FORMAT)
        sdf.parse(dateInUTC)
    } catch (e: Exception) {
        Date()
    }
}

fun formatDate(date: Date): String {
    return try {
        val sdf = SimpleDateFormat(AppConstant.DATE_TIME_FORMAT)
        sdf.format(date)
    } catch (e: Exception) {
        ""
    }
}
