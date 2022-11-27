package com.example.lilium.Map

import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Canvas
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.fragment.app.FragmentActivity
import com.example.lilium.R
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.*
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog


class GoogleMapActivity : AppCompatActivity(), OnMapReadyCallback {

    private var mMap: GoogleMap? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_map)
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment?
        if (mapFragment != null) {
            mapFragment!!.getMapAsync(this)
        }


    }

    override fun onMapReady(googleMap: GoogleMap) {

        mMap = googleMap

        for (userLocation in Constants.k_listUserLocation.indices) {
            // Add a marker on user location
            val sydney = LatLng(
                Constants.k_listUserLocation[userLocation].lat!!.toDouble(),
                Constants.k_listUserLocation[userLocation].lang!!.toDouble()
            )
            val addMarker = mMap!!.addMarker(
                MarkerOptions()
                    .position(sydney)
                    .icon(bitmapFromVector(this, R.drawable.ic_location))
            )

            // store data in list
            if (addMarker != null) {
                val userRecord = UserLocation(
                    "",
                    "",
                    "",
                    Constants.k_listUserLocation[userLocation].name.toString(),
                    Constants.k_listUserLocation[userLocation].surname.toString(),
                    Constants.k_listUserLocation[userLocation].type.toString()
                )

                Constants.k_theMap[addMarker] =
                    userRecord
            }

        }

        //handle click listner on marker
        mMap!!.setOnMarkerClickListener { marker ->
            if (marker.isInfoWindowShown) {
                Toast.makeText(this@GoogleMapActivity, "IsInfoWindow", Toast.LENGTH_SHORT).show()
                marker.hideInfoWindow()
            } else {
                for (i in Constants.k_theMap) {
                    if (marker == i.key) {
                        displayBottomSheet(this, i.value.name, i.value.surname, i.value.type)
                    }
                }

                marker.showInfoWindow()
            }
            true
        }

        // zoom google map on exact location
        mMap!!.animateCamera(
            CameraUpdateFactory.newLatLngZoom(
                LatLng(
                    Constants.k_listUserLocation[1].lat!!.toDouble(),
                    Constants.k_listUserLocation[1].lang!!.toDouble()
                ), 10.0f
            )
        )
    }

    // handle bottom sheet
    private fun displayBottomSheet(
        activity: FragmentActivity,
        name: String?,
        surname: String?,
        type: String?,
    ) {
        val viewGroup = activity.findViewById<ViewGroup>(android.R.id.content)
        val dialogView: View =
            LayoutInflater.from(activity).inflate(R.layout.bottom_sheet, viewGroup, false)
        val tvName = dialogView.findViewById<TextView>(R.id.tvName)
        val etSurname = dialogView.findViewById<TextView>(R.id.tvSurName)
        val ivType = dialogView.findViewById<TextView>(R.id.tvType)

        val builder = BottomSheetDialog(activity)
        builder.setContentView(dialogView)
        builder.setCancelable(true)
        val show = builder.show()

        builder.setOnShowListener {
            val dialog = it as BottomSheetDialog
            val bottomSheet =
                dialog.findViewById<View>(com.google.android.material.R.id.design_bottom_sheet)
            bottomSheet?.let { sheet ->
                dialog.behavior.state = BottomSheetBehavior.STATE_EXPANDED
                sheet.parent.parent.requestLayout()
            }
        }

        tvName.text = name
        etSurname.text = surname
        ivType.text = type

    }

    private fun bitmapFromVector(context: Context, vectorResId: Int): BitmapDescriptor? {
        // below line is use to generate a drawable.
        val vectorDrawable = ContextCompat.getDrawable(context, vectorResId)

        // below line is use to set bounds to our vector drawable.
        vectorDrawable!!.setBounds(
            0,
            0,
            vectorDrawable.intrinsicWidth,
            vectorDrawable.intrinsicHeight
        )

        // below line is use to create a bitmap for our
        // drawable which we have added.
        val bitmap = Bitmap.createBitmap(
            vectorDrawable.intrinsicWidth,
            vectorDrawable.intrinsicHeight,
            Bitmap.Config.ARGB_8888
        )

        // below line is use to add bitmap in our canvas.
        val canvas = Canvas(bitmap)

        // below line is use to draw our
        // vector drawable in canvas.
        vectorDrawable.draw(canvas)

        // after generating our bitmap we are returning our bitmap.
        return BitmapDescriptorFactory.fromBitmap(bitmap)
    }

}


