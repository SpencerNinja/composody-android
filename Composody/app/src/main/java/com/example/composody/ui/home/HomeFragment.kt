package com.example.composody.ui.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.NumberPicker
import android.widget.NumberPicker.OnValueChangeListener
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.composody.R
import com.example.composody.Scale
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

        val application = requireNotNull(this.activity).application

        val viewModelFactory = HomeViewModelFactory(application)

        // Notes NumberPicker = How many notes?
        var noteCount = homeViewModel.noteCount
        var notePicker = binding.numberPicker
        notePicker.minValue = noteCount[0].toInt()
        notePicker.maxValue = noteCount.size
        notePicker.displayedValues = noteCount.toTypedArray()

        // Store the number picked
        var notePicked = noteCount[0]
        notePicker.setOnValueChangedListener(OnValueChangeListener { numberPicker, i, i1 ->
            val numPositionPicked: Int = notePicker.getValue()
            notePicked = numPositionPicked.toString()
            Log.i("note", "number picked: $notePicked ")
        })

        // Scale NumberPicker = What scale to pull notes from?
        var collectionOfScales = Scale()
        var scaleBank = collectionOfScales.returnListOfScaleNames()
        var scalePicker = root.findViewById<NumberPicker>(R.id.scale_picker)
        scalePicker.minValue = 0
        scalePicker.maxValue = scaleBank.size - 1
        scalePicker.displayedValues = scaleBank.toTypedArray()

        // Store the scale picked
        var scalePicked = scaleBank[0]
        scalePicker.setOnValueChangedListener(OnValueChangeListener { _, _, _ ->
            val scalePositionPicked: Int = scalePicker.getValue()
            scalePicked = scaleBank[scalePositionPicked]
            Log.i("note", "scale picked: $scalePicked ")
        })

        // Mood NumberPicker = What patterns to use?
        var moodBank = homeViewModel.moodBank
        var moodPicker = root.findViewById<NumberPicker>(R.id.pattern_picker)
        moodPicker.minValue = 0
        moodPicker.maxValue = moodBank.size - 1
        moodPicker.displayedValues = moodBank.toTypedArray()

        // Store the mood pattern picked
        var moodPicked = moodBank[0]
        moodPicker.setOnValueChangedListener(OnValueChangeListener { _, _, _ ->
            val moodPositionPicked: Int = moodPicker.getValue()
            moodPicked = moodBank[moodPositionPicked]
            Log.i("note", "mood pattern picked: $moodPicked ")
        })

        // OnClickListener for "Generate" melody button
        root.findViewById<Button>(R.id.button_generate_melody).setOnClickListener {
            // Create the melody
            homeViewModel.createMelody()
        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}