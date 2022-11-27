package com.example.lilium.Map


import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.lilium.Map.Utils.Companion.isOnline
import com.example.lilium.R


@SuppressLint("CustomSplashScreen")
class SplashActivity : AppCompatActivity() {

    companion object {
        private val TAG = SplashActivity::class.java.simpleName
        private var i = 0
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        getUserData()


    }

    // get data all data from persons
    private fun getUserData() {

        //check internet connection
        if (!isOnline()) {
            return
        }

        Constants.rootRef.collection(Constants.k_persons)
            .get().addOnSuccessListener { result ->
                for (i in result) {
                    Constants.k_listUserLocation.add(
                        UserLocation(
                            i.id,
                            i.data["lat"] as String,
                            i.data["long"] as String,
                            i.data["name"] as String,
                            i.data["surname"] as String,
                            i.data["type"] as String,
                        )
                    )
                }
                moveToNextActivity()
            }
    }

    private fun moveToNextActivity() {
        startActivity(Intent(this, GoogleMapActivity::class.java))

    }

    //close app on double click of android back button
    override fun onBackPressed() {
        if (i == 0) {
            i++
            Toast.makeText(this, "Press back again to exit.", Toast.LENGTH_SHORT).show()
        } else {
            val a = Intent(Intent.ACTION_MAIN)
            a.addCategory(Intent.CATEGORY_HOME)
            a.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            startActivity(a)
        }
    }
}

