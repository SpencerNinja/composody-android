package com.example.composody.ui.about

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.composody.R
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.example.composody.databinding.FragmentAboutBinding


class MapView : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap
    private lateinit var binding: FragmentAboutBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = FragmentAboutBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        val dixietech = LatLng(37.09860, -113.59165)
        mMap.addMarker(MarkerOptions().position(dixietech).title("Marker at Dixie Tech University"))
        mMap.moveCamera(CameraUpdateFactory.newLatLng(dixietech))
    }
}