package com.example.hackerschool_app

import android.content.Context
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class SettingsActivity : AppCompatActivity() {

    lateinit var UserInfo_View : TextView
    lateinit var UserInfo : String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val sharedPref = getSharedPreferences("UserPref", Context.MODE_PRIVATE)
        theme.applyStyle(sharedPref.getInt("Tema", R.style.MainTheme),true)
        setContentView(R.layout.activity_settings)

        val user : TextView = findViewById(R.id.textView4)

        val searchButton : Button = findViewById(R.id.Search_button)
        val etfirstname : EditText = findViewById(R.id.search_name)
        val etlastname : EditText = findViewById(R.id.search_last_name)
        val resultado : TextView= findViewById(R.id.textView5)

        searchButton.setOnClickListener {
            val first : String = etfirstname.text.toString()
            val last : String = etlastname.text.toString()

            //O TEXTO DEFAULT CASO NÃO ENCONTRE A SHAREDPREFERENCE PODE SER DADO COMO PARAMETRO DO getString()
            user.setText(sharedPref.getString(first + last,"No User has been found with that name."))
            //O TEXTO A DIZER SEARCH RESULTS PODIA SER FEITO DIRETAMENTE NO XML MAS ASSIM AQUI APENAS APARECE APÓS CLICAR NO BOTÃO
            resultado.setText("Search Results:")
        }

    }
}