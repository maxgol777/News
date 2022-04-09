package com.example.newstestapp.feature_news.data.utils

import java.time.LocalDate
import java.time.format.DateTimeFormatter

object Util {
    fun currentDate(): String {
        val today: LocalDate = LocalDate.now()
        return today.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))
    }
}