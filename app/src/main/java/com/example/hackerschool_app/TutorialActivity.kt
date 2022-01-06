package com.example.hackerschool_app

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class TutorialActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val sharedPref = getSharedPreferences("UserPref", Context.MODE_PRIVATE)
        theme.applyStyle(sharedPref.getInt("Tema", R.style.MainTheme),true)
        setContentView(R.layout.activity_tutorial)
    }
}