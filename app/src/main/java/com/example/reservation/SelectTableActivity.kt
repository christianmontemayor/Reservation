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

        val oldbundle = intent.extras

        val bundle = Bundle()

        if (oldbundle != null) {
            findViewById<TextView>(R.id.resName).text = oldbundle.getString("resName")
            findViewById<TextView>(R.id.resEmail).text = oldbundle.getString("resEmail")
            findViewById<TextView>(R.id.resPhoneNumber).text = oldbundle.getString("resPhoneNumber")
            findViewById<TextView>(R.id.resDate).text = oldbundle.getString("resDate")
            findViewById<TextView>(R.id.resTime).text = oldbundle.getString("resTime")
            findViewById<TextView>(R.id.resGuests).text = oldbundle.getString("resGuests")
        }



        bundle.putString("resName", findViewById<TextView>(R.id.resName).text.toString())
        bundle.putString("resEmail", findViewById<TextView>(R.id.resEmail).text.toString())
        bundle.putString("resPhoneNumber", findViewById<TextView>(R.id.resPhoneNumber).text.toString())
        bundle.putString("resDate", findViewById<TextView>(R.id.resDate).text.toString())
        bundle.putString("resTime", findViewById<TextView>(R.id.resTime).text.toString())
        bundle.putString("resGuests", findViewById<TextView>(R.id.resGuests).text.toString())





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
        val numberOfColumns = 6
        recyclerView.layoutManager = GridLayoutManager(this, numberOfColumns)
        adapter = SelectTableViewActivity(this, data)
        adapter!!.setClickListener(this)
        recyclerView.adapter = adapter

        val intent = Intent(this, ConfirmReservationActivity::class.java)
        intent.putExtras(bundle)
    }

    override fun onItemClick(view: View?, position: Int, size: Int) {
        setContentView(R.layout.activity_select_table)
        val reservation: Reservation = userDatabase.getReservation()


        val oldBundle = intent.extras
        if (oldBundle != null) {

            val numGuests = oldBundle.getString("resGuests")
            val exists = userDatabase.exists((position + 1).toString())
            if (numGuests != null) {
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

