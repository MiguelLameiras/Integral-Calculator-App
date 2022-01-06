package com.example.hackerschool_app

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity

@Suppress("DEPRECATION")
class SplashScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //IR BUSCAR QUAL TEMA ESTÁ ATIVO, O TEMA ATIVO É GUARDADO NUMA SHAREDPREFERENCE
        val sharedPref = getSharedPreferences("UserPref", Context.MODE_PRIVATE)
        theme.applyStyle(sharedPref.getInt("Tema", R.style.MainTheme),true)

        setContentView(R.layout.activity_splash_screen)

        //DEFINIR O LAYOUT COMO FULLSCREEN PARA O SPLASH SCREEN, É O STANDART UTILIZADO IG
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )

        //TEMPO QUE DURA ESTA ACTIVITY, APOS O TEMPO PASSAR MUDAR DE ATIVIDADE
        Handler().postDelayed({
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }, 3000)

    }
}