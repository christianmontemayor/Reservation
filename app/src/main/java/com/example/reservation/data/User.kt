package com.example.reservation.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_table")
data class User(
    @PrimaryKey(autoGenerate = true)
    val preferredDinerId: Int,
    val username: String,
    val password: String,
    val name: String,
    val mailingAddress: String,
    val billingAddress: String,
    val earnedPoints: Int,
    val preferredPaymentMethod: Int
)

@Entity(tableName = "reservation_table")
data class Reservation(
    @PrimaryKey(autoGenerate = true)
    val preferredResId: Int,
    val name: String,
    val email: String,
    val phoneNumber: String,
    val date: String,
    val time: String,
    val numberGuest: String,
    val tableNumber: String
)