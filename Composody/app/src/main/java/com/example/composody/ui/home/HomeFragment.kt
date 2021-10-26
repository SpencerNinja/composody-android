package com.example.composody.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.composody.R
import com.example.composody.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel
    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)

        // Inflate view
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        // OnClickListener for "Generate" melody button
        root.findViewById<Button>(R.id.button_generate_melody).setOnClickListener {
            // Create the melody
            homeViewModel.createMelody()
        }

        // Notes (How many notes?) NumberPicker
        var noteCount = listOf("3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13")
        var notePicker = binding.numberPicker
        notePicker.minValue = noteCount[0].toInt()
        notePicker.maxValue = noteCount.size
        notePicker.displayedValues = noteCount.toTypedArray()

        // Scale (What scale to pull notes from?) NumberPicker
        var data2 = listOf("3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13")
        var picker2 = binding.numberPicker
        picker2.minValue = data2[0].toInt()
        picker2.maxValue = data2.size
        picker2.displayedValues = data2.toTypedArray()

        // Display text depending on current page
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