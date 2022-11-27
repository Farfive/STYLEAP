package com.example.lilium.Select


import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.lilium.Form.Form
import com.example.lilium.Map.SplashActivity
import com.example.lilium.R


class Select : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_select)
        val klientbtn = findViewById<Button>(R.id.klientbtn)
        val firmabtn = findViewById<Button>(R.id.firmabtn)



        klientbtn.setOnClickListener {
            val intent = Intent(this, SplashActivity::class.java)
            startActivity(intent)
        }



    }
}


