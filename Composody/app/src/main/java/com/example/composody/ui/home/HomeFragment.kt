package com.example.composody.ui.home

import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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

    // How many notes
    var melodyLength = 7
    // Create an empty list to later store notes
    var notes = mutableListOf<Note>()

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

        createMelody()

        homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)

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

    fun createMelody(): List<Note> {

        // Choose a random scale from the list of scales
        var scale = Scale()
        var selectedScale = scale.selectRandomScale()
        Log.i("note", "randomFrequencyIndex = ${selectedScale}")

        // create a note with a frequency value and add it to a new list
        for (n1 in 1..melodyLength) {

            // Initialize a note instance
            var note = Note()
            note.toneObject = PerfectTune()

            // Generate a random frequency from the selected scale
            var randomFrequencyIndex = Random.nextInt(selectedScale.size)
            var randomFrequency = selectedScale[randomFrequencyIndex]
            Log.i("note", "randomFrequencyIndex = ${randomFrequencyIndex}")
            Log.i("note", "randomFrequency = ${randomFrequency}")
            note.frequency = randomFrequency

            var randomDuration = Random.nextInt(500, 2000)
            note.duration = randomDuration

            notes.add(note)
        }
        return notes
    }

    fun startCoolDown(note: Note, seconds: Int) {
        object: CountDownTimer(seconds*1000.toLong(), seconds*1000.toLong()) {
            override fun onTick(millisUntilFinished: Long) {}
            override fun onFinish() {
                note.toneObject.stopTune()
            }
        }.start()
    }

    /**
     * PLAY button
     */
    fun playMelody(view: View) {
        var index = 0
        if (view.id == R.id.button_sound_play) {
            object: CountDownTimer((melodyLength*1000).toLong(), 1000) {
                override fun onTick(millisUntilFinished: Long) {
                    Log.i("note", "note frequency: ${notes[index].frequency}")
                    notes[index].assignFrequency()
                    notes[index].playFreq()
                    startCoolDown(notes[index],1)
                    index += 1
                }
                override fun onFinish() {
                    return
                }
            }.start()
        }
    }

    /**
     * STOP button
     */
//    fun stopTune(view: View) {
//        if (view.id == R.id.button_sound_stop) {
//            //stops the tune
////            note.toneObject.stopTune()
//        }
//    }

}