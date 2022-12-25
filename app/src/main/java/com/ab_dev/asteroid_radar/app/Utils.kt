package com.ab_dev.asteroid_radar.app

import android.annotation.SuppressLint
import java.text.SimpleDateFormat
import java.util.*

@SuppressLint("WeekBasedYear")
fun getFormattedCurrentDate() : String {
    val calendar = Calendar.getInstance()
    val currentTime = calendar.time
    val dateFormat = SimpleDateFormat(Constants.API_QUERY_DATE_FORMAT, Locale.getDefault())
    return dateFormat.format(currentTime)
}