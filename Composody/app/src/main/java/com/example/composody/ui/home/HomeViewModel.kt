package com.example.composody.ui.home

import android.app.Application
import android.os.CountDownTimer
import android.util.Log
import android.view.View
import androidx.databinding.Observable
import androidx.databinding.PropertyChangeRegistry
import androidx.lifecycle.*
import com.example.composody.Note
import com.example.composody.R
import com.example.composody.Scale
import com.karlotoy.perfectune.instance.PerfectTune
import kotlin.random.Random

class HomeViewModel(
    application: Application
) : AndroidViewModel(application), Observable {

    /**
     * Melody Length scroll wheel
     */
    // Data to load into melody note count scroll wheel
    var noteCount = listOf("3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15",
        "16", "17", "18", "19", "20", "21")

    // Live Data for melody note count
    private val _countPickedLive = MutableLiveData<Int>()
    val countPickedLive: LiveData<Int>
        get() = _countPickedLive

    // Set live data for melody note count picked
    fun setCountLiveData(count: Int) {
        _countPickedLive.value = count
    }


    /**
     * Scale scroll wheel
     */
    // Live Data for selected scale (position in list)
    private val _scalePickedLive = MutableLiveData<String?>()
    val scalePickedLive: LiveData<String?>
        get() = _scalePickedLive

    // Set live data for scale picked
    fun setScaleLiveData(scale: String) {
        _scalePickedLive.value = scale
    }


    /**
     * Mood/Pattern scroll wheel
     */
    // Data to load into mood/pattern scroll wheel
    var moodBank = listOf("Rocky", "Dangerous", "Soaring", "Rainy Day", "Lullaby")

    // Mood Live Data
    private val _moodPickedLive = MutableLiveData<String>()
    val moodPickedLive: LiveData<String>
        get() = _moodPickedLive

    // Set live data for scale picked
    fun setMoodLiveData(mood: String) {
        _moodPickedLive.value = mood
    }


    /**
     * Generate a Melody
     */
    // Create an empty list to later store notes
    var notes = mutableListOf<Note>()
    // Live Data for generate melody to display
    private val _displayNotes = MutableLiveData<List<Note>>()
    val displayNotes: LiveData<List<Note>>
        get() = _displayNotes

    private fun generateRandomNote(selectedScale: List<Double>) {
        // Initialize a note instance
        var note = Note()
        note.toneObject = PerfectTune()
//        // Generate a random frequency from the selected scale
//        Log.i("note", "generateRandomNote - selectedScale = ${selectedScale.size}")
        if (selectedScale == null) {
            selectedScale = listOf(246.9417, 261.6256, 277.1826, 293.6648, 329.6276, 349.2282, 369.9944, 391.9954, 415.3047, 440.0000, 466.1638, 493.8833, 523.2511)
        } else {
            var randomFrequencyIndex = Random.nextInt(selectedScale.size)
            var randomFrequency = selectedScale[randomFrequencyIndex]
            note.frequency = randomFrequency
        }
        var randomDuration = Random.nextInt(500, 2000)
        note.duration = randomDuration
        notes.add(note)
        Log.i("note", "createMelody - frequency index = $randomFrequencyIndex")
        Log.i("note", "createMelody - frequency = $randomFrequency")
    }

    // Generate a random melody
    fun createMelody() {
        // Clear out previous generated melody
        _displayNotes.value = listOf<Note>()
        // Initialize melody and scale to default values on scrollwheel
        var melodyLength = 3
        var scale = Scale()
        var selectedScale = scale.listOfScales[0]
        // Check if melody length is null
        if (_countPickedLive.value == null) {
            _countPickedLive.value = 3
            Log.i("note", "createMelody - _countPickedLive.value is null = ${_countPickedLive.value}")
        }
        // Set melody length to value from scroll wheel (live data)
        melodyLength = _countPickedLive.value!!
        Log.i("note", "createMelody - melody length = $melodyLength")
        // Check if scale is null
        if (_scalePickedLive.value == null) {
            _scalePickedLive.value = scale.listOfScales[0].toString()
            Log.i("note", "createMelody - _scalePickedLive.value is null = ${_scalePickedLive.value}")
        }
        // Set scale to value from scroll wheel (live data)
        selectedScale = scale.returnSelectedScale(_scalePickedLive.value!!)
        Log.i("note", "createMelody - scale frequencies = $selectedScale")
        // Create a note with a frequency value and add it to the melody note list
        for (n1 in 1..melodyLength!!) {
            // Generate a PerfectTune, select a random frequency from scale, and add to list of notes
            generateRandomNote(selectedScale)
        }
        // Update the melody (a list of note frequencies)
        Log.i("note", "createMelody - notes = $notes")
        _displayNotes.value = notes
        Log.i("note", "createMelody - _displayNotes = ${_displayNotes.value}")
//        return notes
    }


    /**
     * Play melody audio
     */
    // TODO: What does this do?
    fun startCoolDown(note: Note, seconds: Int) {
        object: CountDownTimer(seconds*1000.toLong(), seconds*1000.toLong()) {
            override fun onTick(millisUntilFinished: Long) {}
            override fun onFinish() {
                note.toneObject.stopTune()
            }
        }.start()
    }

    // Start melody playback
    fun playMelody(view: View) {
        val melodyLength = countPickedLive.value
        var index = 0
        if (view.id == R.id.button_play_melody) {
            object: CountDownTimer((melodyLength!!.times(1000)).toLong(), 1000) {
                override fun onTick(millisUntilFinished: Long) {
                    Log.i("note", "note frequency = ${notes[index].frequency}")
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
     * Stop melody audio
     */
    // Stop melody playback
//    fun stopTune(view: View) {
//        if (view.id == R.id.button_sound_stop) {
//            //stops the tune
////            note.toneObject.stopTune()
//        }
//    }


    /**
     * Data Binding
     */
    // Necessary for Data Binding
    private val callbacks: PropertyChangeRegistry by lazy { PropertyChangeRegistry()}
    override fun removeOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {
        callbacks.add(callback)
    }
    override fun addOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {
        callbacks.remove(callback)
    }

}