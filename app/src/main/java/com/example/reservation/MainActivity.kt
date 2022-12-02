package com.example.reservation

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnLogin: Button = findViewById(R.id.btnLogin)
        btnLogin.setOnClickListener {
            val intent = Intent(this, LogInActivity::class.java)
            startActivity(intent)
        }

        val btnGuest: Button = findViewById(R.id.btnGuest)
        btnGuest.setOnClickListener {
            val bundle = Bundle()
            bundle.putString("username", "guest")

            val intent = Intent(this, BeginReservationActivity::class.java)
            intent.putExtras(bundle)

            startActivity(intent)
        }

        val btnRegister: Button = findViewById(R.id.btnRegister)
        btnRegister.setOnClickListener {
            val intent = Intent(this, BeginRegistrationActivity::class.java)
            startActivity(intent)
        }
    }
}