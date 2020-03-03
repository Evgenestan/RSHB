package com.example.myapplication

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_third.*


class thirdActivity : AppCompatActivity() {

    val data_file = "data"

    lateinit var pref: SharedPreferences

    override fun onBackPressed() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_third)
        pref = getSharedPreferences(data_file, Context.MODE_PRIVATE)
        val name = pref.getString("name","no")
        testtext.setText("$name, Спасибо за заказ. Оператор свяжется с вами.")


    }
}
