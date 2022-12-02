package com.example.reservation

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import java.text.DateFormat
import java.text.SimpleDateFormat

class BeginRegistrationActivity : AppCompatActivity() {
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
            if(findViewById<EditText>(R.id.resName).text.isEmpty() ||
                findViewById<EditText>(R.id.editTextPhone).text.isEmpty() ||
                findViewById<EditText>(R.id.editTextDate).text.isEmpty() ||
                findViewById<EditText>(R.id.editGuests).text.isEmpty()) {

                Toast.makeText(applicationContext,
                    "Please enter all fields",
                    Toast.LENGTH_SHORT).show()
            }
            else if(!isPhoneValid(findViewById<EditText>(R.id.editTextPhone).text.toString())) {
                Toast.makeText(applicationContext,
                    "Please enter a valid phone number",
                    Toast.LENGTH_SHORT).show()
            } else if(!isDateValid(findViewById<EditText>(R.id.editTextDate).text.toString())) {
                Toast.makeText(applicationContext,
                    "Please enter a valid date",
                    Toast.LENGTH_SHORT).show()
            } else if(!isGuestsValid(findViewById<EditText>(R.id.editGuests).text.toString())) {
                Toast.makeText(applicationContext,
                    "Please enter a valid amount of guests",
                    Toast.LENGTH_SHORT).show()
            } else {
                val bundle = Bundle()
                bundle.putString("resName", findViewById<EditText>(R.id.resName).text.toString())
                bundle.putString("phone", findViewById<EditText>(R.id.editTextPhone).text.toString())
                bundle.putString("date", findViewById<EditText>(R.id.editTextDate).text.toString())
                bundle.putString("guests", findViewById<EditText>(R.id.editGuests).text.toString())
                if (oldBundle != null) {
                    bundle.putString("user", oldBundle.get("username") as String?)
                }

                val intent = Intent(this, SelectTableActivity::class.java)
                intent.putExtras(bundle)

                startActivity(intent)
            }
        }
    }

    private fun isPhoneValid(number: String): Boolean {
        return number.length in 10..11 &&
                android.util.Patterns.PHONE.matcher(number).matches()
    }

    private fun isDateValid(date: String): Boolean {
        return try {
            val df: DateFormat = SimpleDateFormat("dd/MM/yy")
            df.isLenient = false
            df.parse(date)
            true
        } catch (e: Exception) {
            println(e.toString())
            Toast.makeText(applicationContext,
                e.toString(),
                Toast.LENGTH_LONG).show()
            false
        }
    }

    private fun isGuestsValid(guests: String): Boolean {
        return try {
            Integer.parseInt(guests) <= 99
        } catch (e: Exception) {
            false
        }
    }
}