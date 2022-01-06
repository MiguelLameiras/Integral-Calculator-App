package com.example.hackerschool_app

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.bumptech.glide.Glide
import com.chaquo.python.Python
import com.chaquo.python.android.AndroidPlatform

class MainActivity : AppCompatActivity() {

    //VARIAVEIS NECESSARIAS PARA MAIS TARDE CRIAR UMA NOTIFICAÇÃO
    lateinit var notificationManager: NotificationManager
    lateinit var notificationChannel: NotificationChannel
    lateinit var builder: Notification.Builder
    private val channelId = "i.apps.notifications"
    private val description = "Test notification"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //IR BUSCAR QUAL TEMA ESTÁ ATIVO, O TEMA ATIVO É GUARDADO NUMA SHAREDPREFERENCE
        val sharedPref = getSharedPreferences("UserPref", Context.MODE_PRIVATE)
        theme.applyStyle(sharedPref.getInt("Tema", R.style.MainTheme),true)

        setContentView(R.layout.activity_main)

        //IR BUSCAR FOTO DA NASA'S IMAGE OF THE DAY COM O VOLLEY
        val queue = Volley.newRequestQueue(this)
        val jsonObjectRequest = JsonObjectRequest(Request.Method.GET, "https://api.nasa.gov/planetary/apod?api_key=sQeqfRdf7Ic3nctMtpOeKHigcMg1WjOvfYdGfVRl", null, {response ->
            val astronomyImage=response.getString("hdurl")
            Glide.with(this)
                .asDrawable()
                .load(astronomyImage)
                .into(findViewById(R.id.Nasa_img))
        },{
            Toast.makeText(this,"Something Went Wrong", Toast.LENGTH_SHORT).show()
        })
        queue.add(jsonObjectRequest)

        //BUTOES E TAL ABAIXO
        val btnExplorar : Button = findViewById(R.id.btn_explore)

        btnExplorar.setOnClickListener {
            val intent = Intent(this, ExploreActivity::class.java)
            startActivity(intent)
        }

        val btnIntegral : Button = findViewById(R.id.btn_atividade)

        btnIntegral.setOnClickListener {
            val intent = Intent(this, IntegralActivity::class.java)
            startActivity(intent)
        }

        val btncolors : Button = findViewById(R.id.btn_colors)

        btncolors.setOnClickListener {
            val intent = Intent(this, ColorsActivity::class.java)
            startActivity(intent)
        }

        val btnsettings : Button = findViewById(R.id.btn_settings)

        btnsettings.setOnClickListener {
            val intent = Intent(this, SettingsActivity::class.java)
            startActivity(intent)
        }

        val btnsetup : Button = findViewById(R.id.btn_setup)

        btnsetup.setOnClickListener {
            val intent = Intent(this, SetupActivity::class.java)
            startActivity(intent)
        }

        //INICIAR O SCRIPT DO PYTHON
        initPython()

        //NOTIFICAÇÃO CADA VEZ QUE SE ABRE A APP
        notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            notificationChannel = NotificationChannel(channelId, description, NotificationManager.IMPORTANCE_HIGH)
            notificationChannel.enableLights(true)
            notificationChannel.lightColor = Color.GREEN
            notificationChannel.enableVibration(false)
            notificationManager.createNotificationChannel(notificationChannel)

            builder = Notification.Builder(this, channelId)
                .setSmallIcon(android.R.drawable.alert_dark_frame)
                .setLargeIcon(BitmapFactory.decodeResource(this.resources, R.drawable.ic_launcher_foreground))
                .setContentText("Welcome! Press the Explore button to view Nasa's Picture off the Day")
        }
        notificationManager.notify(1234, builder.build())

    }

    //CRIAR FUNÇÃO QUE INICIE O INTERPRETADOR DE PYTHON
    private fun initPython(){
        if(!Python.isStarted())
        {
            Python.start(AndroidPlatform(this))
        }
    }
}