package com.example.reservation

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class BeginRegistrationActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        val finalRegisterBtn: Button = findViewById(R.id.finalRegistrationBtn)
        finalRegisterBtn.setOnClickListener {
            if(findViewById<EditText>(R.id.new_name).text.isEmpty() ||
                findViewById<EditText>(R.id.new_email).text.isEmpty() ||
                findViewById<EditText>(R.id.new_phone).text.isEmpty() ||
                findViewById<EditText>(R.id.address).text.isEmpty() ||
                findViewById<EditText>(R.id.user).text.isEmpty() ||
                findViewById<EditText>(R.id.pswd).text.isEmpty() ||
                findViewById<EditText>(R.id.confirm_password).text.isEmpty()) {

                Toast.makeText(applicationContext,
                    "Please enter all fields",
                    Toast.LENGTH_SHORT).show()
            }
            else if(!isPhoneValid(findViewById<EditText>(R.id.new_phone).text.toString())) {
                Toast.makeText(applicationContext,
                    "Please enter a valid phone number",
                    Toast.LENGTH_SHORT).show()
            } else if(!passwordMatch(findViewById<EditText>(R.id.confirm_password).text.toString())) {
                Toast.makeText(applicationContext,
                    "Passwords do not match",
                    Toast.LENGTH_SHORT).show()
            } else {
                val bundle = Bundle()
                bundle.putString("name", findViewById<EditText>(R.id.new_name).text.toString())
                bundle.putString("email", findViewById<EditText>(R.id.new_email).text.toString())
                bundle.putString("phone", findViewById<EditText>(R.id.new_phone).text.toString())
                bundle.putString("address", findViewById<EditText>(R.id.address).text.toString())
                bundle.putString("username", findViewById<EditText>(R.id.user).text.toString())
                bundle.putString("password", findViewById<EditText>(R.id.pswd).text.toString())


                val intent = Intent(this, FinishRegistrationActivity::class.java)
                intent.putExtras(bundle)

                startActivity(intent)
            }
        }
    }

    private fun isPhoneValid(number: String): Boolean {
        return number.length in 10..11 &&
                android.util.Patterns.PHONE.matcher(number).matches()
    }

    private fun passwordMatch(password: String): Boolean {
        return try {
            findViewById<EditText>(R.id.pswd).text.toString() == password
        } catch (e: Exception) {
            false
        }
    }
}