package com.example.composody.ui.home

import android.app.Application
import android.os.CountDownTimer
import android.util.Log
import android.widget.TextView
import androidx.databinding.Observable
import androidx.databinding.PropertyChangeRegistry
import androidx.lifecycle.*
import com.example.composody.data.Note
import com.example.composody.data.Patterns
import com.example.composody.data.Scale
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

    // Last used index of note
    var lastUsedIndex = 0

    private fun updateMelodyTextViewDisplay() {
        _displayNotes.value = notes
    }

    private fun assignFrequencyAndDuration(note: Note, noteFrequency: Double) {
        note.frequency = noteFrequency
        var randomDuration = Random.nextInt(500, 2000)
        note.duration = randomDuration
    }

    private fun createNoteInstance(): Note {
        var note = Note()
        note.toneObject = PerfectTune()
        return note
    }

    fun generateMelodyBasedOnSelectedMood(): List<Double> {
        var melody = mutableListOf<Double>()
        val rocky = listOf<List<Double>>(ascend(melody),descend(melody))
        val dangerous = listOf<List<Double>>(fibonacci(melody), pi(melody), heartbeat(melody))
        val lullaby = listOf<List<Double>>(waltz(melody))
        val soaring = listOf<List<Double>>(ascend(melody), ascendTriad(melody), descend(melody),
            descendTriad(melody), deku(melody), pyramid(melody), steps(melody))
        val rainyDay = listOf<List<Double>>(skip(melody))
        val listOfMoods = mapOf("rocky" to rocky, "dangerous" to dangerous, "lullaby" to lullaby,
            "soaring" to soaring, "rainyDay" to rainyDay)
        var chosenMood = rocky
        for ((key, value) in listOfMoods) {
            if (key == moodPickedLive.value) {
                chosenMood = value
            }
        }
        checkMelodyLength(melody, chosenMood)
        return melody
    }

    private fun checkIfScaleIsNull(scale: Scale) {
        if (_scalePickedLive.value == null) {
            _scalePickedLive.value = scale.listOfScales[0].toString()
        }
    }

    private fun checkIfMelodyLengthIsNull() {
        if (_countPickedLive.value == null) {
            _countPickedLive.value = 3
        }
    }

    private fun clearOutPreviouslyGeneratedMelody(generatedList: TextView) {
        if (generatedList.text != "") {
            _displayNotes.value = listOf()
            notes = mutableListOf()
        }
    }

    fun generateMelody(generatedList: TextView) {
        clearOutPreviouslyGeneratedMelody(generatedList)
        checkIfMelodyLengthIsNull()
        var scale = Scale()
        checkIfScaleIsNull(scale)
        var selectedScale = scale.returnSelectedScale(_scalePickedLive.value!!)

        val finishedMelody = generateMelodyBasedOnSelectedMood()

        updateMelodyTextViewDisplay()
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
    fun playMelody() {
        val melodyLength = countPickedLive.value
        var index = 0
        object: CountDownTimer((melodyLength!!.times(1000)).toLong(), 1000) {
            override fun onTick(millisUntilFinished: Long) {
                // function to assign and play frequency note
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


    // ==========================================================================================


    /**
     * MOOD/PATTERN
     */
    // Ascend(1,2,3,4)
    private fun ascend(melody: MutableList<Double>): List<Double> {
        var scale = Scale()
        var selectedScale = scale.returnSelectedScale(_scalePickedLive.value!!)
        val stretchOfNotes = (2..4).random()
        var note = 0.0
        var count = 0
        while (count < stretchOfNotes) {
            val newIndex = lastUsedIndex + 1
            lastUsedIndex = newIndex
            note = selectedScale[newIndex]
            melody.add(note)
            count += 1
        }
        return melody
    }
    // Descend(4,3,2,1)
    private fun descend(melody: MutableList<Double>): List<Double> {
        var scale = Scale()
        var selectedScale = scale.returnSelectedScale(_scalePickedLive.value!!)
        val stretchOfNotes = (2..4).random()
        var note = 0.0
        var count = 0
        while (count < stretchOfNotes) {
            val newIndex = lastUsedIndex - 1
            lastUsedIndex = newIndex
            note = selectedScale[newIndex]
            melody.add(note)
            count += 1
        }
        return melody
    }
    // 3. 141 592 653 589 793 238 462 643 3
    // Pi (3,1,4,1,5,9,2,6,5,3,5,8,9,7,9,3,2,3,8,4,6)
    private fun pi(melody: MutableList<Double>): List<Double> {
        var scale = Scale()
        var selectedScale = scale.returnSelectedScale(_scalePickedLive.value!!)
        val stretchOfNotes = (2..21).random()
        var note = 0.0
        var piValue = listOf(3,1,4,1,5,9,2,6,5,3,5,8,9,7,9,3,2,3,8,4,6)
        var count = 0
        while (count < stretchOfNotes) {
            for (index in piValue) {
                val newIndex = piValue[index]
                lastUsedIndex = newIndex
                note = selectedScale[newIndex]
                melody.add(note)
                count += 1
            }
        }
        return melody
    }
    // Fibonacci (0,1,1,2,3,5,8)
    private fun fibonacci(melody: MutableList<Double>): List<Double> {
        var scale = Scale()
        var selectedScale = scale.returnSelectedScale(_scalePickedLive.value!!)
        val stretchOfNotes = (2..7).random()
        var note = 0.0
        var fibonacciValue = listOf(0,1,1,2,3,5,8)
        var count = 0
        while (count < stretchOfNotes) {
            for (index in fibonacciValue) {
                val newIndex = fibonacciValue[index]
                lastUsedIndex = newIndex
                note = selectedScale[newIndex]
                melody.add(note)
                count += 1
            }
        }
        return melody
    }
    // Heartbeat (2,3,1,7,0,2,4,2)
    private fun heartbeat(melody: MutableList<Double>): List<Double> {
        var scale = Scale()
        var selectedScale = scale.returnSelectedScale(_scalePickedLive.value!!)
        val stretchOfNotes = (2..8).random()
        var note = 0.0
        var heartbeatValue = listOf(2,3,1,7,0,2,4,2)
        var count = 0
        while (count < stretchOfNotes) {
            for (index in heartbeatValue) {
                val newIndex = heartbeatValue[index]
                lastUsedIndex = newIndex
                note = selectedScale[newIndex]
                melody.add(note)
                count += 1
            }
        }
        return melody
    }
    // Waltz (2,3,3,1,2,2)
    private fun waltz(melody: MutableList<Double>): List<Double> {
        var scale = Scale()
        var selectedScale = scale.returnSelectedScale(_scalePickedLive.value!!)
        val stretchOfNotes = (2..12).random()
        var note = 0.0
        var count = 0
        var newIndex = lastUsedIndex
        while (count < stretchOfNotes) {
            if (count % 2 == 0) {
                newIndex = lastUsedIndex + 0
            } else {
                newIndex = lastUsedIndex + 1
            }
            if (count % 3 == 0) {
                val jump = listOf(-3,-2,-1,0,1,2,3).random()
                newIndex = lastUsedIndex + jump
            }
            lastUsedIndex = newIndex
            note = selectedScale[newIndex]
            melody.add(note)
            count += 1
        }
        return melody
    }
    // Triad Ascend (1,2,3)
    private fun ascendTriad(melody: MutableList<Double>): List<Double> {
        var scale = Scale()
        var selectedScale = scale.returnSelectedScale(_scalePickedLive.value!!)
        val stretchOfNotes = (3..6).random()
        var note = 0.0
        var newIndex = lastUsedIndex
        var count = 0
        while (count < stretchOfNotes) {
            if (count % 3 == 0) {
                newIndex = lastUsedIndex + 0
                lastUsedIndex = newIndex
                note = selectedScale[newIndex]
            } else if (count % 3 == 1) {
                newIndex = lastUsedIndex + 1
                lastUsedIndex = newIndex
                note = selectedScale[newIndex]
            } else {
                newIndex = lastUsedIndex + 2
                lastUsedIndex = newIndex
                note = selectedScale[newIndex]
                lastUsedIndex -= 3
            }
            melody.add(note)
            count += 1
        }
        return melody
    }
    // Triad Descend (3,2,1)
    private fun descendTriad(melody: MutableList<Double>): List<Double> {
        var scale = Scale()
        var selectedScale = scale.returnSelectedScale(_scalePickedLive.value!!)
        val stretchOfNotes = (3..6).random()
        var note = 0.0
        var newIndex = lastUsedIndex
        var count = 0
        while (count < stretchOfNotes) {
            if (count % 3 == 0) {
                newIndex = lastUsedIndex + 0
                lastUsedIndex = newIndex
                note = selectedScale[newIndex]
            } else if (count % 3 == 1) {
                newIndex = lastUsedIndex - 1
                lastUsedIndex = newIndex
                note = selectedScale[newIndex]
            } else {
                newIndex = lastUsedIndex - 2
                lastUsedIndex = newIndex
                note = selectedScale[newIndex]
                lastUsedIndex += 3
            }
            melody.add(note)
            count += 1
        }
        return melody
    }
    // Pyramid (1,3,2)
    private fun pyramid(melody: MutableList<Double>): List<Double> {
        var scale = Scale()
        var selectedScale = scale.returnSelectedScale(_scalePickedLive.value!!)
        val stretchOfNotes = (3..6).random()
        var note = 0.0
        var newIndex = lastUsedIndex
        var count = 0
        while (count < stretchOfNotes) {
            if (count % 3 == 0) {
                newIndex = lastUsedIndex + 0
                lastUsedIndex = newIndex
                note = selectedScale[newIndex]
            } else if (count % 3 == 1) {
                newIndex = lastUsedIndex + 2
                lastUsedIndex = newIndex
                note = selectedScale[newIndex]
            } else {
                newIndex = lastUsedIndex - 1
                lastUsedIndex = newIndex
                note = selectedScale[newIndex]
                lastUsedIndex -= 1
            }
            melody.add(note)
            count += 1
        }
        return melody
    }
    // Deku (2,3,1)
    private fun deku(melody: MutableList<Double>): List<Double> {
        var scale = Scale()
        var selectedScale = scale.returnSelectedScale(_scalePickedLive.value!!)
        val stretchOfNotes = (3..6).random()
        var note = 0.0
        var newIndex = lastUsedIndex
        var count = 0
        while (count < stretchOfNotes) {
            if (count % 3 == 0) {
                newIndex = lastUsedIndex + 1
                lastUsedIndex = newIndex
                note = selectedScale[newIndex]
            } else if (count % 3 == 1) {
                newIndex = lastUsedIndex + 1
                lastUsedIndex = newIndex
                note = selectedScale[newIndex]
            } else {
                newIndex = lastUsedIndex - 2
                lastUsedIndex = newIndex
                note = selectedScale[newIndex]
                lastUsedIndex += 1
            }
            melody.add(note)
            count += 1
        }
        return melody
    }
    // Steps (1,1,2,2,3,3)
    private fun steps(melody: MutableList<Double>): List<Double> {
        var scale = Scale()
        var selectedScale = scale.returnSelectedScale(_scalePickedLive.value!!)
        val stretchOfNotes = (2..12).random()
        var note = 0.0
        var count = 0
        var newIndex = lastUsedIndex
        while (count < stretchOfNotes) {
            if (count % 3 == 0) {
                newIndex = lastUsedIndex + 0
            } else if (count % 3 == 1) {
                newIndex = lastUsedIndex + 1
            }
            if (count % 3 == 2) {
                val jump = listOf(-2,-1,1,2).random()
                newIndex = lastUsedIndex + jump
            }
            lastUsedIndex = newIndex
            note = selectedScale[newIndex]
            melody.add(note)
            count += 1
        }
        return melody
    }
    // Skip (1,1,2,2,1,1,2,2)
    private fun skip(melody: MutableList<Double>): List<Double> {
        var scale = Scale()
        var selectedScale = scale.returnSelectedScale(_scalePickedLive.value!!)
        val stretchOfNotes = (2..12).random()
        var note = 0.0
        var count = 0
        var newIndex = lastUsedIndex
        while (count < stretchOfNotes) {
            if (count % 3 == 0) {
                newIndex = lastUsedIndex + 0
            } else if (count % 3 == 1) {
                newIndex = lastUsedIndex + 1
            }
            if (count % 3 == 2) {
                newIndex = lastUsedIndex - 1
            }
            lastUsedIndex = newIndex
            note = selectedScale[newIndex]
            melody.add(note)
            count += 1
        }
        return melody
    }

    private fun checkForLastGeneratedNote(melody: MutableList<Double>): Double {
        var scale = Scale()
        var selectedScale = scale.returnSelectedScale(_scalePickedLive.value!!)
        var lastGeneratedNote = 0.0
        val middleIndex = selectedScale.size / 2
        if (melody.size == 0) {
            lastGeneratedNote = selectedScale[middleIndex]
            lastUsedIndex = middleIndex
        } else {
            lastGeneratedNote = melody.last()
            var index = -1
            for (freq in selectedScale) {
                index += 1
                if (freq == lastGeneratedNote) {
                    lastUsedIndex = index
                }
            }
        }
        return lastGeneratedNote
    }
    // chosenMood = listOf(listOf<Double>())
    private fun randomlySelectPattern(chosenMood: List<List<Double>>){
        var randomlySelectedPattern = chosenMood.random()
    }
    // countPickedLive
    private fun checkMelodyLength(melody: MutableList<Double>, chosenMood: List<List<Double>>) {
        var totalNotes = countPickedLive.value
        while (melody.size < totalNotes!!) {
            randomlySelectPattern(chosenMood)
        }
        if (melody.size > totalNotes) {
            val howMuchOverTotalNotes = melody.size - totalNotes
            // remove (melody.size - totalNotes) from end of melody
            while (howMuchOverTotalNotes > 0) {
                melody.removeLast()
            }
        }
    }



    // __________________________________________________________________________________________


    /**
     * Stop melody audio playback
     */
    fun stopTune(note: Note) {
            note.toneObject.stopTune()
    }


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