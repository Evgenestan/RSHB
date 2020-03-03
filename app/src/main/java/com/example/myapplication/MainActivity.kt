package com.example.myapplication

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import java.io.File


class MainActivity : AppCompatActivity() {

    val data_file = "data"
    var changetext = 0


    lateinit var pref: SharedPreferences



    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)




        pref = getSharedPreferences(data_file, Context.MODE_PRIVATE)
        val editor = pref.edit()

        if(pref.getString("name","no") != "no")
        {
            changetext++

            switch2.isChecked = true
        }

        when (changetext) { //описание этапа
            0 -> textmain.setText("Первым этапом является заявка в приложении, это займёт не больше двух минут!")
            1 -> textmain.setText("Второй этап это рассмотрение вашей заявки банком.")
        }

        clear.setOnClickListener() //кнопка для очистки данных, для отладки
        {

            editor.clear()
            editor.apply()
            switch2.isChecked = false
            this.recreate()

        }
        start.setOnClickListener() //кнопка "хочу карту"
        {


            val intent = Intent(
                this,
                SecondActivity::class.java)
            startActivity(intent)
            finish()

        }

    }


}
