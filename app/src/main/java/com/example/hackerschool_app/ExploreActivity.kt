package com.example.hackerschool_app

import android.content.Context
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.bumptech.glide.Glide


class ExploreActivity : AppCompatActivity() {
    lateinit var title : TextView
    lateinit var date : TextView
    lateinit var explanation : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val sharedPref = getSharedPreferences("UserPref", Context.MODE_PRIVATE)
        theme.applyStyle(sharedPref.getInt("Tema", R.style.MainTheme),true)
        setContentView(R.layout.activity_explore)

        //Ir buscar foto com o volley
        val queue = Volley.newRequestQueue(this)
        val jsonObjectRequest = JsonObjectRequest(Request.Method.GET, "https://api.nasa.gov/planetary/apod?api_key=sQeqfRdf7Ic3nctMtpOeKHigcMg1WjOvfYdGfVRl", null, { response ->
                val astronomyTitle = response.getString("title")
                title = findViewById(R.id.Nasa_title)
                title.text = astronomyTitle.toString()
                val astronomyDate = response.getString("date")
                date = findViewById(R.id.Nasa_date)
                date.text = astronomyDate.toString()
                val astronomyImageDecription = response.getString("explanation")
                explanation = findViewById(R.id.Nasa_explanation)
                explanation.text = astronomyImageDecription.toString()
                val astronomyImage=response.getString("hdurl")
                Glide.with(this)
                    .asDrawable()
                    .load(astronomyImage)
                    .into(findViewById(R.id.Nasa_img))
            },{
                Toast.makeText(this,"Something Went Wrong",Toast.LENGTH_SHORT).show()
            })

        queue.add(jsonObjectRequest)

    }
}