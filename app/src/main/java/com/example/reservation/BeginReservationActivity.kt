package com.example.reservation

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity


class BeginReservationActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_begin_reservation)

        val oldBundle = intent.extras

        if (oldBundle != null) {
            val str : String = "Welcome " + oldBundle.get("username")
            findViewById<TextView>(R.id.welcome).text = str
        }

        val btnSubmit: Button = findViewById(R.id.btnSubmit)
        btnSubmit.setOnClickListener {
            val reservationName = findViewById<EditText>(R.id.resName).text
            val reservationEmail = findViewById<EditText>(R.id.editTextEmail).text
            val reservationPhoneNumber = findViewById<EditText>(R.id.editTextPhone).text
            val reservationDate = findViewById<EditText>(R.id.editTextDate).text
            val reservationTime = findViewById<EditText>(R.id.editTextTime).text
            val reservationSize = findViewById<EditText>(R.id.editGuests).text

            if(reservationName.isEmpty() || reservationPhoneNumber.isEmpty() || reservationDate.isEmpty() || reservationSize.isEmpty()) {
                Toast.makeText(applicationContext,
                    "Please enter all fields",
                    Toast.LENGTH_SHORT).show()
            }
            else if(!isPhoneNumberValid(reservationPhoneNumber.toString())) {
                Toast.makeText(applicationContext,
                    "Please enter a valid phone number",
                    Toast.LENGTH_SHORT).show()
            } else if(!isDateValid(reservationDate.toString())) {
                Toast.makeText(applicationContext,
                    "Please enter a valid date",
                    Toast.LENGTH_SHORT).show()
            } else if(!isGuestsValid(reservationSize.toString())) {
                Toast.makeText(applicationContext,
                    "Please enter a valid amount of guests",
                    Toast.LENGTH_SHORT).show()
            } else {

                if(reservationDate.toString() == "07/4/23"){
                    Toast.makeText(applicationContext,
                        "Holiday reservation: valid credit card is necessary, hold fee required, and $10 no show fee",
                        Toast.LENGTH_LONG).show()
                }

                val bundle = Bundle()

                bundle.putString("resName", findViewById<EditText>(R.id.resName).text.toString())
                bundle.putString("resPhoneNumber", findViewById<EditText>(R.id.editTextPhone).text.toString())
                bundle.putString("resEmail", findViewById<EditText>(R.id.editTextEmail).text.toString())
                bundle.putString("resDate", findViewById<EditText>(R.id.editTextDate).text.toString())
                bundle.putString("resTime", findViewById<EditText>(R.id.editTextTime).text.toString())
                bundle.putString("resGuests", findViewById<EditText>(R.id.editGuests).text.toString())

                if (oldBundle != null) {
                    bundle.putString("user", oldBundle.get("username") as String?)
                }

                val intent = Intent(this, SelectTableActivity::class.java)
                intent.putExtras(bundle)

                startActivity(intent)
            }
        }
    }
}