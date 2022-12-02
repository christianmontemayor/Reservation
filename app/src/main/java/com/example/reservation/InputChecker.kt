package com.example.reservation

import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.Locale

fun isPhoneNumberValid(number: String): Boolean {
    return number.length in 10..11
}

fun isDateValid(date: String): Boolean {
    return try {
        val df: DateFormat = SimpleDateFormat("MM/dd/yy", Locale.US)
        df.isLenient = false
        df.parse(date)
        true
    } catch (e: Exception) {
        false
    }
}

fun isGuestsValid(guests: String): Boolean {
    return try {
        Integer.parseInt(guests) in 1 .. 99
    } catch (e: Exception) {
        false
    }
}