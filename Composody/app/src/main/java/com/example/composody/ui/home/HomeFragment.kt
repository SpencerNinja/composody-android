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
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.composody.R
import com.example.composody.Scale
import com.example.composody.databinding.FragmentHomeBinding
import com.example.composody.utils.NoteFrequency

class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel

    // This property is only valid between onCreateView and onDestroyView.
    private var _binding: FragmentHomeBinding? = null
    private val binding
        get() = _binding!!

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

        // HomeViewModelFactory
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
            Log.i("note", "inside Observer - countPickedLive = $count")
        })

        // Store the melody length number selected from the scroll wheel
        notePicker.setOnValueChangedListener(OnValueChangeListener { numberPicker, i, i1 ->
            val numPositionPicked: Int = notePicker.getValue()
            homeViewModel.setCountLiveData(numPositionPicked)
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
            Log.i("note", "inside Observer - scalePickedLive = $scale")
        })

        // Store the selected scale from the scroll wheel
        var scalePicked = scaleBank[0]
        scalePicker.setOnValueChangedListener(OnValueChangeListener { _, _, _ ->
            val scalePositionPicked: Int = scalePicker.getValue()
            scalePicked = scaleBank[scalePositionPicked]
            homeViewModel.setScaleLiveData(scalePicked)
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
            Log.i("note", "inside Observer - moodPickedLive = $mood")
        })

        // Store the mood pattern selected from the scroll wheel
        moodPicker.setOnValueChangedListener(OnValueChangeListener { _, _, _ ->
            val moodPositionPicked: Int = moodPicker.getValue()
            var moodPicked = moodBank[moodPositionPicked]
            homeViewModel.setMoodLiveData(moodPicked)
        })


        /**
         * Button to generate a melody
         */
        // OnClickListener for "Generate" melody button
        root.findViewById<Button>(R.id.button_generate_melody).setOnClickListener {
            // Create the melody
            homeViewModel.generateMelody(binding.generatedList)
        }


        /**
         * Observer for generated melody
         */
        // Observe
        homeViewModel.displayNotes.observe(viewLifecycleOwner, Observer {
            it?.let { melody ->
                binding.generatedList.text = melody.map {
                    NoteFrequency.mapOfNoteFrequencies[it.frequency]
                }.toString()
            }
        })


        /**
         * Button to play generated melody
         */
        root.findViewById<Button>(R.id.button_play_melody).setOnClickListener {
            homeViewModel.playMelody()
            Log.i("note", "play button clicked")
        }



        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}