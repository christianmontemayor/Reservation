package com.example.reservation

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.example.reservation.data.Reservation
import com.example.reservation.data.User
import com.example.reservation.data.UserDatabase
import java.text.SimpleDateFormat
import java.util.*

class ConfirmReservationActivity : AppCompatActivity() {
    private val userDatabase by lazy { UserDatabase.getDatabase(this).userDao() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_confirm_reservation)

        val oldbundle = intent.extras

        if (oldbundle != null) {
            findViewById<TextView>(R.id.resName).text = oldbundle.getString("resName")
            findViewById<TextView>(R.id.resEmail).text = oldbundle.getString("resEmail")
            findViewById<TextView>(R.id.resPhoneNumber).text = oldbundle.getString("resPhoneNumber")
            findViewById<TextView>(R.id.resDate).text = oldbundle.getString("resDate")
            findViewById<TextView>(R.id.resTime).text = oldbundle.getString("resTime")
            findViewById<TextView>(R.id.resGuests).text = oldbundle.getString("resGuests")
            findViewById<TextView>(R.id.resTable).text = oldbundle.getString("resTable")
        }

        try {
            userDatabase.addReservation(Reservation(5346734,"John Doe", "example@gmail.com", "2819487217","12/2/22","2:00","5","2") )

        } catch(e: java.lang.Exception) {
            println(e.toString())
            Toast.makeText(applicationContext,
                e.toString(),
                Toast.LENGTH_LONG).show()
        }



        val btnLogin: Button = findViewById(R.id.btnConfirm)
        btnLogin.setOnClickListener {
            val name = findViewById<TextView>(R.id.resName).text.toString()
            val email = findViewById<TextView>(R.id.resEmail).text.toString()
            val phoneNumber = findViewById<TextView>(R.id.resPhoneNumber).text.toString()
            val date = findViewById<TextView>(R.id.resDate).text.toString()
            val time = findViewById<TextView>(R.id.resTime).text.toString()
            val guests = findViewById<TextView>(R.id.resGuests).text.toString()
            val table = findViewById<TextView>(R.id.resTable).text.toString()

            val id = table + date.replace("/","") + time

            userDatabase.addReservation(Reservation(id.toInt(),name,email,phoneNumber,date,time,guests,table) )

            val intent = Intent(this, MainActivity::class.java)

            startActivity(intent)

        }
    }
}
