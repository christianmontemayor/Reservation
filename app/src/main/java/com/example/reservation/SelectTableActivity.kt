package com.example.reservation


import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.reservation.data.Reservation
import com.example.reservation.data.User
import com.example.reservation.data.UserDatabase


class SelectTableActivity : AppCompatActivity(), SelectTableViewActivity.ItemClickListener {
    private val userDatabase by lazy { UserDatabase.getDatabase(this).userDao() }
    var adapter: SelectTableViewActivity? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_select_table)

        val bundle = intent.extras

        // data to populate the RecyclerView with
        val data = arrayOf(
            "2",
            "2",
            "2",
            "4",
            "4",
            "4",
            "6",
            "6",
            "6",
            "8"

        )


        // set up the RecyclerView
        val recyclerView = findViewById<RecyclerView>(R.id.rvNumbers)
        val numberOfColumns = 4
        recyclerView.layoutManager = GridLayoutManager(this, numberOfColumns)
        adapter = SelectTableViewActivity(this, data)
        adapter!!.setClickListener(this)
        recyclerView.adapter = adapter

        val intent = Intent(this, ConfirmReservationActivity::class.java)
        if (bundle != null) {
            intent.putExtras(bundle)
        }
    }

    override fun onItemClick(view: View?, position: Int, size: Int) {
        setContentView(R.layout.activity_select_table)
        val reservation: Reservation = userDatabase.getReservation()


        val oldBundle = intent.extras
        if (oldBundle != null) {

            val numGuests = oldBundle.getString("resGuests")
            val date = oldBundle.getString("resDate")
            val time = oldBundle.getString("resTime")

            if (numGuests != null && date != null && time !=null) {
                val table_string = (position + 1).toString()
                val date_string =  date.replace("/","")
                val time_string =  time.replace(":","")
                val exists = userDatabase.exists((table_string + date_string + time_string).toInt())
                if (numGuests.toInt() <= size && !exists) {
                    val intent = Intent(this, ConfirmReservationActivity::class.java)
                    oldBundle.putString("resTable", (position + 1).toString())
                    intent.putExtras(oldBundle)
                    startActivity(intent)

                } else{
                    val intent = Intent(this, SelectTableActivity::class.java)
                    intent.putExtras(oldBundle)
                    startActivity(intent)
               }

            }
        }
    }
}

