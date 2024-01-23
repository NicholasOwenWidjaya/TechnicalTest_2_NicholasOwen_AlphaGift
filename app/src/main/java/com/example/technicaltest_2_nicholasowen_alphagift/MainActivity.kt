package com.example.technicaltest_2_nicholasowen_alphagift

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.InputType
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//      Login Check
//      Username = alfagift-admin
//      Password = asdf
        val user = "alfagift-admin"
        val pass = "asdf"

        val lgnbtn = findViewById<Button>(R.id.lgnbtn)
        val username = findViewById<EditText>(R.id.usernameLgn)
        val password = findViewById<EditText>(R.id.passwordLgn)
        val usernamecv = findViewById<CardView>(R.id.usernameCV)
        val errorwarning = findViewById<TextView>(R.id.errorwarning)
        val normalbg = ContextCompat.getColor(this, R.color.white)
        val normaltxt = ContextCompat.getColor(this, R.color.black)
        val errortxt = ContextCompat.getColor(this, R.color.errortxt)

        lgnbtn.setOnClickListener() {
            val usernameT = username.text.toString().trim()
            val passwordT = password.text.toString().trim()

            if (usernameT.isEmpty()) {
                username.setHint("Username can't be empty!")
                username.setHintTextColor(errortxt)
            } else if (usernameT.isNotEmpty()) {
                username.setHint("Username")
                username.setHintTextColor(normaltxt)
            }

            if (passwordT.isEmpty()) {
                password.setHint("Password can't be empty!")
                password.setHintTextColor(errortxt)
            } else if (passwordT.isNotEmpty()) {
                password.setHint("Password")
                password.setHintTextColor(normaltxt)
            }

            if (usernameT == user && passwordT == pass) {
                val intent = Intent(this, StudentActivity::class.java)
                startActivity(intent)
            } else if (usernameT.isNotEmpty() && passwordT.isNotEmpty()){
                errorwarning.setText("Username or password is incorrect!")
            }
        }

//      Password Seek Feature

        val seek = findViewById<ImageView>(R.id.passwordSeek)
        var isOn = false

        seek.setOnClickListener() {
            if (!isOn) {
                password.inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD
                seek.setImageResource(R.drawable.baseline_remove_red_eye_24_click)
                isOn = true
            } else if (isOn){
                password.inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD
                seek.setImageResource(R.drawable.baseline_remove_red_eye_24)
                isOn = false
            }
        }
    }
}