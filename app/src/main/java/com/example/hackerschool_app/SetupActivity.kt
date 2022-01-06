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
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.RadioButton
import android.widget.RadioGroup
import androidx.appcompat.app.AppCompatActivity

class SetupActivity : AppCompatActivity() {

    lateinit var notificationManager: NotificationManager
    lateinit var notificationChannel: NotificationChannel
    lateinit var builder: Notification.Builder
    private val channelId = "i.apps.notifications"
    private val description = "Test notification"

    lateinit var  radioGroup: RadioGroup
    lateinit var radioButton: RadioButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val sharedPref = getSharedPreferences("UserPref", Context.MODE_PRIVATE)
        theme.applyStyle(sharedPref.getInt("Tema", R.style.MainTheme),true)
        setContentView(R.layout.activity_setup)

        radioGroup = findViewById(R.id.group)

        val firstname : EditText = findViewById(R.id.inName)
        val lastname : EditText = findViewById(R.id.inLastName)
        val curso : EditText = findViewById(R.id.inCurso)
        val date : EditText = findViewById(R.id.editTextDate)
        val submitbtn : Button = findViewById(R.id.submit_btn)

        submitbtn.setOnClickListener {
            //QUANDO O BOTAO SUBMIT É CLICADO RECOLHER O INPUT DOS EDIT TEXT
            val first_name : String = firstname.text.toString()
            val last_name : String = lastname.text.toString()
            val curso_txt : String = curso.text.toString()
            val date_txt : String = date.text.toString()

            //DETETAR QUAL DOS RADIO BUTTONS FOI SELECIONADO
            val radioId : Int = radioGroup.getCheckedRadioButtonId()
            radioButton = findViewById(radioId)
            val gender : String = radioButton.text.toString()

            val editor = sharedPref.edit()

            editor.apply {
                //ALTERAR/CRIAR SHARED PREFERENCE COM A INFORMAÇÃO DADA COMO INPUT NO FORMULARIO
                putString(first_name + last_name,"First Name: " +  first_name + "\nLast Name:" + last_name + "\nCourse: " + curso_txt + "\nBirth Date: " + date_txt + "\nGender: " + gender)
                apply()
            }

            //NOTIFICAÇÃO QUANDO O USER FOI CRIADO
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
                    .setContentText("A new user has been created successfully!")
            }
            notificationManager.notify(1234, builder.build())

            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

    }

    //FUNÇÃO QUE DETETA QUAL DOS RADIO BUTTONS FOI CLICADO
    fun checkButton(v: View){
        val radioId : Int = radioGroup.getCheckedRadioButtonId()
    }
}