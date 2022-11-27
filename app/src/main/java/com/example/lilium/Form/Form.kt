package com.example.lilium.Form

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.example.lilium.Profile.ProfileActivity
import com.example.lilium.R
import com.example.lilium.databinding.ActivityFormBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.StorageReference
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext


class Form : AppCompatActivity() {
    private lateinit var binding: ActivityFormBinding
    private lateinit var database: DatabaseReference
    private val personCollectionRef = Firebase.firestore.collection("persons")

    private lateinit var uid: String
    private lateinit var storageReference: StorageReference
    private lateinit var auth: FirebaseAuth


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFormBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val spinnerFor = arrayListOf("Paznokcie", "Makijarz", "Masaż", "Tatuaż")

        val spinerFormAdapter =
            ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, spinnerFor)
        val spinnerForm = findViewById<Spinner>(R.id.spinnerForm)
        spinnerForm.adapter = spinerFormAdapter

        spinnerForm.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {

            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                TODO("Not yet implemented")
            }

        }


        val btnsave = findViewById<Button>(R.id.btnsave)
        val btnForm = findViewById<Button>(R.id.btnForm)
        val name = findViewById<EditText>(R.id.name)
        val surname = findViewById<EditText>(R.id.surname)
        val type = findViewById<EditText>(R.id.type)


        //ustawić przejście do następnego activity

        btnForm.setOnClickListener {
            val intent = Intent(this, ProfileActivity::class.java)
            startActivity(intent)
        }
        btnsave.setOnClickListener {
            val name = name.text.toString()
            val surname = surname.text.toString()
            val type = type.text.toString()
            val person = Person(name, surname, type)
            savePerson(person)
        }

    }

    private fun savePerson(person: Person) = CoroutineScope(Dispatchers.IO).launch {
        try {
            personCollectionRef.add(person).await()
            withContext(Dispatchers.Main) {
                Toast.makeText(this@Form, "Successfully saved data.", Toast.LENGTH_LONG).show()
            }
        } catch(e: Exception) {
            withContext(Dispatchers.Main) {
                Toast.makeText(this@Form, e.message, Toast.LENGTH_LONG).show()
            }
        }
    }



}














