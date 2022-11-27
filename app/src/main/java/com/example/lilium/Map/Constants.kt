package com.example.lilium.Map

import android.annotation.SuppressLint
import com.google.android.gms.maps.model.Marker
import com.google.firebase.firestore.FirebaseFirestore


object Constants {
    //FirebaseRootRef

    @SuppressLint("StaticFieldLeak")
    internal val rootRef = FirebaseFirestore.getInstance()

    //FirebaseDatabaseTables
    internal const val k_persons = "persons"

    //SocialLinksList
    internal val k_listUserLocation = mutableListOf<UserLocation>()
    internal val k_theMap = HashMap<Marker, UserLocation>()

    internal val k_addMarker = mutableListOf<Marker>()
}