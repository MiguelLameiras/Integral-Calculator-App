package com.example.hackerschool_app

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.chaquo.python.Python

class IntegralActivity : AppCompatActivity() {

    lateinit var Texto : TextView
    lateinit var funcao : String
    lateinit var a : String
    lateinit var b : String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val sharedPref = getSharedPreferences("UserPref", Context.MODE_PRIVATE)
        theme.applyStyle(sharedPref.getInt("Tema", R.style.MainTheme),true)
        setContentView(R.layout.activity_integral)

        val showButton : Button = findViewById(R.id.Integral_button)
        val editText : EditText = findViewById(R.id.Integral_expression)
        val editText_a : EditText = findViewById(R.id.Integral_a)
        val editText_b : EditText = findViewById(R.id.Integral_b)

        showButton.setOnClickListener {
            funcao = editText.text.toString()
            a = editText_a.text.toString()
            b = editText_b.text.toString()
            Texto = findViewById(R.id.Integral_txt)
            //DEFINIR O TEXTO COMO O RESULTADO DO get_Expression()
            Texto.text = get_Expression()
        }

        //BOTAO PARA ABRIR O TUTORIAL
        val TutorialButton : Button = findViewById(R.id.Tutorial_btn)

        TutorialButton.setOnClickListener {
            val intent = Intent(this, TutorialActivity::class.java)
            startActivity(intent)
        }

    }

    //FUNÇÃO QUE QUANDO CHAMADA CORRE O SCRIPT PYTHON E RETORNA O VALOR CALCULADO COMO UMA STRING
    private fun get_Expression(): String{
        val python = Python.getInstance()
        val pythonFile = python.getModule("Integral_Calculator")
        return pythonFile.callAttr("Integral",funcao,a,b).toString()
    }

}