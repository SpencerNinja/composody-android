package com.example.composody.ui.home

import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.NumberPicker
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.composody.Note
import com.example.composody.R
import com.example.composody.Scale
import com.example.composody.databinding.FragmentHomeBinding
import com.karlotoy.perfectune.instance.PerfectTune
import kotlin.random.Random

class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel
    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

//        // NumberPicker
//        var data = listOf("2", "3", "4", "5", "6", "7", "8", "9", "10")
//        var picker = binding.numberPicker
//        picker.minValue = data[0].toInt()
//        picker.maxValue = data.size
//        picker.displayedValues = data.toTypedArray()

        homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)

        homeViewModel.createMelody()

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

//        val textView: TextView = binding.textHome
//        homeViewModel.text.observe(viewLifecycleOwner, Observer {
//            textView.text = it
//        })
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}