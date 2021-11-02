package com.example.composody.ui.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.NumberPicker
import android.widget.NumberPicker.OnValueChangeListener
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
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

        /**
         * Inflate layout and connect ViewModel
         */
        // Create a connection to the HomeViewModel
        homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)

        // Inflate view
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val application = requireNotNull(this.activity).application

        val viewModelFactory = HomeViewModelFactory(application)


        /**
         * Melody Length scroll wheel
         */
        // Load the melody length count into the NumberPicker scroll wheel
        var noteCount = homeViewModel.noteCount
        var notePicker = binding.numberPicker
        notePicker.minValue = noteCount[0].toInt()
        notePicker.maxValue = noteCount.size + 2
        notePicker.displayedValues = noteCount.toTypedArray()

        // Observer for Live Data
        homeViewModel.countPickedLive.observe(viewLifecycleOwner, Observer { count ->
            Log.i("note", "inside Observer = $count")
        })

        // Store the melody length number selected from the scroll wheel
        notePicker.setOnValueChangedListener(OnValueChangeListener { numberPicker, i, i1 ->
            val numPositionPicked: Int = notePicker.getValue()
            homeViewModel.setCountLiveData(numPositionPicked)
            Log.i("note", "inside listener - countPickedLive = ${homeViewModel.countPickedLive.value}")
        })


        /**
         * Scale scroll wheel
         */
        //  Load the scale names into the NumberPicker scroll wheel
        var collectionOfScales = Scale()
        var scaleBank = collectionOfScales.returnListOfScaleNames()
        var scalePicker = root.findViewById<NumberPicker>(R.id.scale_picker)
        scalePicker.minValue = 0
        scalePicker.maxValue = scaleBank.size - 1
        scalePicker.displayedValues = scaleBank.toTypedArray()

        // Observer for Live Data
        homeViewModel.scalePickedLive.observe(viewLifecycleOwner, Observer { scale ->
//            Log.i("note", "inside Observer: $scale")
        })

        // Store the selected scale from the scroll wheel
        var scalePicked = scaleBank[0]
        scalePicker.setOnValueChangedListener(OnValueChangeListener { _, _, _ ->
            val scalePositionPicked: Int = scalePicker.getValue()
            scalePicked = scaleBank[scalePositionPicked]
            homeViewModel.setScaleLiveData(scalePicked)
            Log.i("note", "scale picked = ${homeViewModel.scalePickedLive.value}")
        })


        /**
         * Mood/Pattern scroll wheel
         */
        // Load the mood/pattern names into the NumberPicker scroll wheel
        var moodBank = homeViewModel.moodBank
        var moodPicker = root.findViewById<NumberPicker>(R.id.pattern_picker)
        moodPicker.minValue = 0
        moodPicker.maxValue = moodBank.size - 1
        moodPicker.displayedValues = moodBank.toTypedArray()

        // Observer for Live Data
        homeViewModel.moodPickedLive.observe(viewLifecycleOwner, Observer { mood ->
//            Log.i("note", "inside Observer: $mood")
        })

        // Store the mood pattern selected from the scroll wheel
        var moodPicked = moodBank[0]
        moodPicker.setOnValueChangedListener(OnValueChangeListener { _, _, _ ->
            val moodPositionPicked: Int = moodPicker.getValue()
            moodPicked = moodBank[moodPositionPicked]
            homeViewModel.setMoodLiveData(moodPicked)
            Log.i("note", "mood pattern picked = ${homeViewModel.moodPickedLive.value}")
        })


        /**
         * Button to generate a melody
         */
        // OnClickListener for "Generate" melody button
        root.findViewById<Button>(R.id.button_generate_melody).setOnClickListener {
            // Create the melody
            homeViewModel.createMelody()
            // TODO: update TextView to show generated melody
            R.id.generated_list = homeViewModel.setDisplayedGeneratedMelodyLiveData(homeViewModel.displayNotes)
        }


        /**
         * Button to play generated melody
         */
        homeViewModel.playMelody(root)


        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}