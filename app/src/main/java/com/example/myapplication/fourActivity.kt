package com.example.myapplication

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_four.*

class fourActivity : AppCompatActivity() {
    val data_file = "data"


    lateinit var pref: SharedPreferences

        override fun onBackPressed() {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }


        override fun onCreate(savedInstanceState: Bundle?) {
         var chk = true
            super.onCreate(savedInstanceState)
            setContentView(R.layout.activity_four)

         pref = getSharedPreferences(data_file, Context.MODE_PRIVATE)

            viewdata.setOnClickListener(){
            data_text_view.setText(pref.getString("surname","no") + "\n" + pref.getString("name","no") + "\n" + pref.getString("middle_name","no")  + "\n" + pref.getString("phone","no") + "\n" + pref.getString("email","no") + "\n" + pref.getString("address","no"))

           if(chk) {
               scroll.visibility = View.VISIBLE
               chk = false
           }
            else
           {
               scroll.visibility = View.INVISIBLE
               chk = true
           }
        }
    }
}
