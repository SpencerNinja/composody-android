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
    var noteCount = listOf("3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13")

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
    private val _scalePickedLive = MutableLiveData<String>()
    val scalePickedLive: LiveData<String>
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

    // Generate a random melody
    // TODO: Break this into multiple functions
    fun createMelody(): List<Note> {
        var melodyLength = 3
        var scale = Scale()
        var selectedScale = scale.listOfScales[0]
        if (_countPickedLive.value == null) {
            Log.i("note", "melody length = $melodyLength")
            Log.i("note", "createMelody - scale frequencies = $selectedScale")
            for (n1 in 1..melodyLength!!) {
                // Initialize a note instance
                var note = Note()
                note.toneObject = PerfectTune()
                // Generate a random frequency from the selected scale
                var randomFrequencyIndex = Random.nextInt(selectedScale.size)
                var randomFrequency = selectedScale[randomFrequencyIndex]
                Log.i("note", "createMelody - frequency index = $randomFrequencyIndex")
                Log.i("note", "createMelody - frequency = $randomFrequency")
                note.frequency = randomFrequency
                var randomDuration = Random.nextInt(500, 2000)
                note.duration = randomDuration
                notes.add(note)
            }
            return notes
        }
        melodyLength = _countPickedLive.value!!
        Log.i("note", "melody length = $melodyLength")
        selectedScale = scale.returnSelectedScale(scalePickedLive.value!!)
        Log.i("note", "createMelody        - scale frequencies = $selectedScale")
        // create a note with a frequency value and add it to a new list
        for (n1 in 1..melodyLength!!) {
            // Initialize a note instance
            var note = Note()
            note.toneObject = PerfectTune()
            // Generate a random frequency from the selected scale
            var randomFrequencyIndex = Random.nextInt(selectedScale.size)
            var randomFrequency = selectedScale[randomFrequencyIndex]
            Log.i("note", "createMelody - frequency index = $randomFrequencyIndex")
            Log.i("note", "createMelody - frequency = $randomFrequency")
            note.frequency = randomFrequency
            var randomDuration = Random.nextInt(500, 2000)
            note.duration = randomDuration
            notes.add(note)
        }
        return notes
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
        if (view.id == R.id.button_generate_melody) {
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