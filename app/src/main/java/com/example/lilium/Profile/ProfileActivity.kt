package com.example.lilium.Profile


import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.lilium.Profile.Gallery.androidstorage.GalleryActivity
import com.example.lilium.R


class ProfileActivity : AppCompatActivity(){




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profil)
        supportActionBar?.hide()




        val btngallery = findViewById<Button>(R.id.btngallery)
        btngallery.setOnClickListener {
            intent = Intent(this, GalleryActivity::class.java)
            startActivity(intent)
        }



    }



}


