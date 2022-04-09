package com.example.newstestapp.feature_news.presentation.util

import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneOffset
import java.time.temporal.ChronoUnit

fun getTimeSpanInDays(publishedAt: String): Long = try {
    val currentDate = LocalDateTime.now()
    val publishedAtInstant = Instant.parse(publishedAt)
    val publishedAtDate = LocalDateTime.ofInstant(publishedAtInstant, ZoneOffset.UTC)
    ChronoUnit.DAYS.between(publishedAtDate, currentDate)
} catch (e: Exception) {
    e.printStackTrace()
    -1
}
