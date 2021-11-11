package com.example.composody.ui.about

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.composody.R
import com.example.composody.databinding.FragmentAboutBinding
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import kotlinx.coroutines.launch

class AboutFragment : Fragment() {

    private val callback = OnMapReadyCallback { googleMap ->
        viewLifecycleOwner.lifecycleScope.launch {
            val zoomAmount = 13.0F

            val target = LatLng(37.09860, -113.59165)
            googleMap.addMarker(target.let { MarkerOptions().position(it).title(target.toString()) })
            googleMap.moveCamera(CameraUpdateFactory.newLatLng(target))
            googleMap.animateCamera(CameraUpdateFactory.zoomTo(zoomAmount))
        }
    }

    private lateinit var aboutViewModel: AboutViewModel
    private var _binding: FragmentAboutBinding? = null
    private val binding get() = _binding!!

    private lateinit var mMap: GoogleMap

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        aboutViewModel =
            ViewModelProvider(this).get(AboutViewModel::class.java)

        _binding = FragmentAboutBinding.inflate(inflater, container, false)
        val root: View = binding.root

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment?
        mapFragment?.getMapAsync(callback)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}