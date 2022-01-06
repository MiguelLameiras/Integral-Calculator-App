package com.example.hackerschool_app

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class ColorsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val sharedPref = getSharedPreferences("UserPref", Context.MODE_PRIVATE)
        theme.applyStyle(sharedPref.getInt("Tema", R.style.MainTheme),true)
        setContentView(R.layout.activity_colors)

        val btn : Button = findViewById(R.id.button)
        val btn2 : Button = findViewById(R.id.button2)
        val btn3 : Button = findViewById(R.id.button3)
        val btn4 : Button = findViewById(R.id.button4)
        val btn5 : Button = findViewById(R.id.button5)

        val editor = sharedPref.edit()

        btn.setOnClickListener {
            //VARIAVEL COM A LOCALIZAÇÃO DO TEMA DESTE BOTÃO
            val tema = R.style.MainTheme
            //UTILIZAR O EDITOR PARA ALTERAR A SHAREDPREFERENCE CORRESPONDENTE A ESTE TEMA
            editor.apply {
                putInt("Tema", tema)
                apply()
            }
            //ABRIR A MAIN ACTIVITY PARA APLICAR O TEMA ESCOLHIDO
            val intent = Intent(this,MainActivity::class.java)
            startActivity(intent)
        }

        btn2.setOnClickListener {
            val tema = R.style.DarkTheme
            editor.apply {
                putInt("Tema", tema)
                apply()
            }
            val intent = Intent(this,MainActivity::class.java)
            startActivity(intent)
        }

        btn3.setOnClickListener {
            val tema = R.style.PastelTheme
            editor.apply {
                putInt("Tema", tema)
                apply()
            }
            val intent = Intent(this,MainActivity::class.java)
            startActivity(intent)
        }

        btn4.setOnClickListener {
            val tema = R.style.AutumnTheme
            editor.apply {
                putInt("Tema", tema)
                apply()
            }
            val intent = Intent(this,MainActivity::class.java)
            startActivity(intent)
        }

        btn5.setOnClickListener {
            val tema = R.style.WineTheme
            editor.apply {
                putInt("Tema", tema)
                apply()
            }
            val intent = Intent(this,MainActivity::class.java)
            startActivity(intent)
        }
    }
}