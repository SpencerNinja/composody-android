package com.example.composody.ui.home

import android.app.Application
import android.os.CountDownTimer
import android.util.Log
import android.view.View
import android.widget.TextView
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

    // Create a Note instance
    private fun createNoteInstance(): Note {
        var note = Note()
        note.toneObject = PerfectTune()
        return note
    }

    // Get randomFrequency from the selected scale
    // TODO: in the future, this will apply pattern
    private fun getRandomFrequency(selectedScale: List<Double>): Double {
        var randomFrequencyIndex = Random.nextInt(selectedScale.size)
        var randomFrequency = selectedScale[randomFrequencyIndex]
        return randomFrequency
    }

    // Assign frequency and duration to Note object
    private fun assignFrequencyAndDuration(note: Note, randomFrequency: Double) {
        note.frequency = randomFrequency
        var randomDuration = Random.nextInt(500, 2000)
        note.duration = randomDuration
    }

    // Add the Note object to list of notes (melody)
    private fun addNoteToMelodyList(selectedScale: List<Double>) {
        var note = createNoteInstance()
        var scale = Scale()
        var randomFrequency = getRandomFrequency(selectedScale)
        assignFrequencyAndDuration(note, randomFrequency)
        notes.add(note)
    }

//    private fun generateRandomNote(selectedScale: List<Double>) {
//        var note = Note()
//        note.toneObject = PerfectTune()
//        var randomFrequencyIndex = Random.nextInt(selectedScale.size)
//        var randomFrequency = selectedScale[randomFrequencyIndex]
//        note.frequency = randomFrequency
//        var randomDuration = Random.nextInt(500, 2000)
//        note.duration = randomDuration
//        notes.add(note)
//        Log.i("note", "createMelody - frequency index = $randomFrequencyIndex")
//        Log.i("note", "createMelody - frequency = $randomFrequency")
//    }

    private fun clearOutPreviouslyGeneratedMelody(generatedList: TextView) {
        if (generatedList.text != "") {
            _displayNotes.value = listOf()
            notes = mutableListOf()
        }
    }

    private fun checkIfMelodyLengthIsNull() {
        if (_countPickedLive.value == null) {
            _countPickedLive.value = 3
        }
    }

//    private fun setDefaultScaleValue(scale: Scale) {
//        var selectedScale = scale.listOfScales[0]
//    }

    private fun checkIfScaleIsNull(scale: Scale) {
        if (_scalePickedLive.value == null) {
            _scalePickedLive.value = scale.listOfScales[0].toString()
        }
    }

//    private fun setScaleToValueFromScrollWheel(scale: Scale, selectedScale: List<Double>) {
//        selectedScale = scale.returnSelectedScale(_scalePickedLive.value!!)
//    }

    // Set default values or call generateRandomNote function
    private fun createMelodyList(selectedScale: List<Double>) {
        if (selectedScale.isNullOrEmpty()) {
            val defaultScale = listOf(246.9417, 261.6256, 277.1826, 293.6648, 329.6276, 349.2282, 369.9944, 391.9954, 415.3047, 440.0000, 466.1638, 493.8833, 523.2511)
            for (n1 in 1.._countPickedLive.value!!) {
//                generateRandomNote(defaultScale)
                addNoteToMelodyList(defaultScale)
            }
        } else {
            // Create a note with a frequency value and add it to the melody note list
            for (n1 in 1.._countPickedLive.value!!) {
                // Generate a PerfectTune, select a random frequency from scale, and add to list of notes
//                generateRandomNote(selectedScale)
                addNoteToMelodyList(selectedScale)
            }
        }
    }

    // Update the melody (a list of note frequencies)
    private fun updateMelodyTextViewDisplay() {
        _displayNotes.value = notes
    }

    fun generateMelody(generatedList: TextView) {
        clearOutPreviouslyGeneratedMelody(generatedList)
        checkIfMelodyLengthIsNull()
        var scale = Scale()
//        setDefaultScaleValue(scale)
        checkIfScaleIsNull(scale)
        var selectedScale = scale.returnSelectedScale(_scalePickedLive.value!!)
        createMelodyList(selectedScale)
        updateMelodyTextViewDisplay()
    }
    
//    // Generate a random melody
//    fun generateMelody(generatedList: TextView) {
//        if (generatedList.text != "") {
//            _displayNotes.value = listOf()
//            notes = mutableListOf()
//        }
//        if (_countPickedLive.value == null) {
//            _countPickedLive.value = 3
//            Log.i("note", "createMelody - _countPickedLive.value is null = ${_countPickedLive.value}")
//        }
//        var scale = Scale()
//        var selectedScale = scale.listOfScales[0]
//        if (_scalePickedLive.value == null) {
//            _scalePickedLive.value = scale.listOfScales[0].toString()
//            Log.i("note", "createMelody - _scalePickedLive.value is null = ${_scalePickedLive.value}")
//        }
//        selectedScale = scale.returnSelectedScale(_scalePickedLive.value!!)
//        Log.i("note", "createMelody - scale frequencies = $selectedScale")
//
//        if (selectedScale.isNullOrEmpty()) {
//            val defaultScale = listOf(246.9417, 261.6256, 277.1826, 293.6648, 329.6276, 349.2282, 369.9944, 391.9954, 415.3047, 440.0000, 466.1638, 493.8833, 523.2511)
//            for (n1 in 1.._countPickedLive.value!!) {
////                generateRandomNote(defaultScale)
//                addNoteToMelodyList(defaultScale)
//            }
//        } else {
//            // Create a note with a frequency value and add it to the melody note list
//            for (n1 in 1.._countPickedLive.value!!) {
//                // Generate a PerfectTune, select a random frequency from scale, and add to list of notes
////                generateRandomNote(selectedScale)
//                addNoteToMelodyList(selectedScale)
//            }
//        }
//        Log.i("note", "createMelody - notes = $notes")
//        _displayNotes.value = notes
//        Log.i("note", "createMelody - _displayNotes = ${_displayNotes.value}")
//    }


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
        object: CountDownTimer((melodyLength!!.times(1000)).toLong(), 1000) {
            override fun onTick(millisUntilFinished: Long) {
                // TODO: function to assign and play frequency note
                Log.i("note", "playMelody - note frequency = ${notes[index].frequency}")
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