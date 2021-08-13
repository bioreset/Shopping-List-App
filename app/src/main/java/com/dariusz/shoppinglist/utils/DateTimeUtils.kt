package com.dariusz.shoppinglist.utils

import android.annotation.SuppressLint
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle

object DateTimeUtils {

    @SuppressLint("NewApi")
    fun determineTodaysDate(): String {
        val now = LocalDateTime.now()
        return now.format(
            DateTimeFormatter.ofLocalizedDateTime(
                FormatStyle.FULL,
                FormatStyle.MEDIUM
            )
        ).toString()
    }

}