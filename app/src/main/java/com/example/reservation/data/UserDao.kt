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

    @Query("SELECT * FROM user_table WHERE username = :arg0 AND password = :arg1")
    fun attemptLogin(arg0: String, arg1: String): User

    @Query("SELECT * FROM reservation_table")
    fun getReservation(): Reservation

    @Query("SELECT EXISTS (SELECT 1 FROM reservation_table WHERE preferredResId = :arg0)")
    fun exists(arg0: Int): Boolean

}