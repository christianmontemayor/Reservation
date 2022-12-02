package com.example.reservation.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun addUser(user: User)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun addReservation(reservation: Reservation)

    @Query("SELECT * FROM user_table WHERE username = :username AND password = :password")
    fun attemptLogin(username: String, password: String): User

    @Query("SELECT * FROM reservation_table")
    fun getReservation(): Reservation

    @Query("SELECT EXISTS (SELECT 1 FROM reservation_table WHERE tableNumber = :tableNumber)")
    fun exists(tableNumber: String): Boolean

}