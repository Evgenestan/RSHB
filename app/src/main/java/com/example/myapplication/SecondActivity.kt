package com.example.myapplication

import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AlertDialog
import com.github.kittinunf.fuel.httpGet
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_second.*
import kotlinx.coroutines.*
import androidx.fragment.app.DialogFragment

class MyDialogFragment : DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let {
            val builder = AlertDialog.Builder(it)
            builder.setMessage("Пожалуйста, подождите")


            builder.create()
        } ?: throw IllegalStateException("Activity cannot be null")
    }

}

class SecondActivity : AppCompatActivity() {
    val data_file = "data"

    lateinit var pref: SharedPreferences

    override fun onBackPressed() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }



        fun upload() = GlobalScope.launch(Dispatchers.IO) {
            val job: String? = GlobalScope.async(Dispatchers.IO) {

                "https://projecttest0.000webhostapp.com/test0"
                    .httpGet(listOf("name" to Name.text.toString()
                        ,"surname" to surname.text.toString()
                        ,"middle_name" to middle_name.text.toString()
                        ,"email" to email.text.toString()
                        ,"phone" to phone.text.toString()
                        ,"address" to address.text.toString()
                    ))
                    .responseString()
            }.await().third.component1()
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        pref = getSharedPreferences(data_file, Context.MODE_PRIVATE)

        val intent1 = Intent(
            this,
            thirdActivity::class.java);

        val intent2 = Intent(
            this,
            fourActivity::class.java);

        val editor = pref.edit()

        val check = pref.getString("name","no")

        if (check != "no") { startActivity(intent2); finish(); } //проверка на уже существующую заявку




        enter.setOnClickListener() {  //кнопка отправить


            val myDialogFragment = MyDialogFragment()
            val manager = supportFragmentManager
            myDialogFragment.show(manager, "myDialog")
            upload()
            editor.putString("name", Name.text.toString())
            editor.putString("surname", surname.text.toString())
            editor.putString("phone", phone.text.toString())
            editor.putString("middle_name", middle_name.text.toString())
            editor.putString("email", email.text.toString())
            editor.putString("address", address.text.toString())


            editor.apply()

            val CheckInput = Name.length() * surname.length() * phone.length() * middle_name.length() * email.length() * address.length() //проверка на отсутсвие незаполненых полей
            if(consent.isChecked and (CheckInput!=0)) {
                startActivity(intent1);
                finish();
            }
            else
            {
                warning.visibility = View.VISIBLE
            }
        }
    }
}
