package com.example.composody.ui.home

import android.app.Application
import android.os.CountDownTimer
import android.util.Log
import android.view.View
import android.widget.NumberPicker
import androidx.databinding.Bindable
import androidx.databinding.Observable
import androidx.databinding.PropertyChangeRegistry
import androidx.lifecycle.*
import com.example.composody.Note
import com.example.composody.R
import com.example.composody.Scale
import com.karlotoy.perfectune.instance.PerfectTune
import java.lang.NumberFormatException
import kotlin.random.Random

class HomeViewModel(
    application: Application
) : AndroidViewModel(application), Observable {

    // Data to load into melody note count scroll wheel
    var noteCount = listOf("3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13")

    // Variable to store user input from scroll wheels
    var countPicked: Int = 0

    // Live Data for melody note count
    private val _countPickedLive = MutableLiveData<Int?>()
    val countPickedLive: LiveData<Int?>
        get() = _countPickedLive

    // How many notes
//    var melodyLength = _countPickedLive
//    private val _melodyLength = Transformations.map(melodyLength) {
//        try {
//            it?.toInt()
//        } catch (e: NumberFormatException) {
//            0
//        }
//    }

//    var scaledPicked: Int? = null

    val scaleSelected = mapOf(1 to "cMajor", 2 to "Pentatonic", 3 to "pentatonic2", 4 to "aMinor",
        5 to "cMajor Pentatonic", 6 to "aMinor Pentatonic", 7 to "Blues", 8 to "Harmonic Minor",
        9 to "Altered Dominant", 10 to "Flamenco", 11 to "Hungarian Minor", 12 to "Persian",
        13 to "Spanish", 14 to "Japanese", 15 to "All Notes")

//    var moodPicked: Int? = null

    // Data to load into mood/pattern scroll wheel
    var moodBank = listOf("Rocky", "Dangerous", "Soaring", "Rainy Day", "Lullaby")

    // Create an empty list to later store notes
    var notes = mutableListOf<Note>()

    // Set live data for melody note count picked
    fun setCountLiveData(count: Int) {
        _countPickedLive.value = count
    }

    // Generate a random melody
    fun createMelody(): List<Note> {
        val melodyLength = countPickedLive.value
        Log.i("note", "melodyLength = $melodyLength")
        // Choose a random scale from the list of scales
        var scale = Scale()
        var selectedScale = scale.selectRandomScale()
        Log.i("note", "randomFrequencyIndex = $selectedScale")
        // create a note with a frequency value and add it to a new list
        for (n1 in 1..melodyLength!!) {
            // Initialize a note instance
            var note = Note()
            note.toneObject = PerfectTune()
            // Generate a random frequency from the selected scale
            var randomFrequencyIndex = Random.nextInt(selectedScale.size)
            var randomFrequency = selectedScale[randomFrequencyIndex]
            Log.i("note", "randomFrequencyIndex = $randomFrequencyIndex")
            Log.i("note", "randomFrequency = $randomFrequency")
            note.frequency = randomFrequency
            var randomDuration = Random.nextInt(500, 2000)
            note.duration = randomDuration
            notes.add(note)
        }
        return notes
    }

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

    // Stop melody playback
//    fun stopTune(view: View) {
//        if (view.id == R.id.button_sound_stop) {
//            //stops the tune
////            note.toneObject.stopTune()
//        }
//    }

    // Necessary for Data Binding
    private val callbacks: PropertyChangeRegistry by lazy { PropertyChangeRegistry()}
    override fun removeOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {
        callbacks.add(callback)
    }
    override fun addOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {
        callbacks.remove(callback)
    }

}