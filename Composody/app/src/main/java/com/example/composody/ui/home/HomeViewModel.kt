package com.example.composody.ui.home

import android.app.Application
import android.os.CountDownTimer
import android.util.Log
import android.widget.TextView
import androidx.databinding.Observable
import androidx.databinding.PropertyChangeRegistry
import androidx.lifecycle.*
import com.example.composody.data.Note
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
//    var notes = mutableListOf<Note>()

    // Live Data for generate melody to display
    private val _displayNotes = MutableLiveData<List<Note>>()
    val displayNotes: LiveData<List<Note>>
        get() = _displayNotes

    // Placeholder for finished melody
    var melody = mutableListOf<Note>()

    // Last used index of note
    var lastUsedIndex = 0


    private fun updateMelodyTextViewDisplay() {
        _displayNotes.value = melody
    }

    private fun checkIfMoodIsNull() {
        if (_moodPickedLive.value == null) {
            _moodPickedLive.value = moodBank[0]
        }
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
            melody = mutableListOf()
        }
    }

    // Pattern: ascend(1,2,3,4)
    private fun ascend(melody: MutableList<Note>) {
        val scale = Scale()
        var selectedScale = scale.returnSelectedScale(_scalePickedLive.value!!)
        if (selectedScale.isNullOrEmpty()) {
            selectedScale = listOf(
                246.9417, 261.6256, 277.1826, 293.6648, 329.6276, 349.2282, 369.9944, 391.9954,
                415.3047, 440.0000, 466.1638, 493.8833, 523.2511
            )
        }
        Log.i("note", "ascend() -> selectedScale = $selectedScale")
        lastUsedIndex = selectedScale.size / 2
        val stretchOfNotes = (2..4).random()
        var count = 0
        var frequency: Double
        val playableNote = Note()
        playableNote.toneObject = PerfectTune()
        while (count < stretchOfNotes) {
            val newIndex = lastUsedIndex + 1
            lastUsedIndex = newIndex
            frequency = selectedScale[newIndex]
            playableNote.frequency = frequency
            val randomDuration = Random.nextInt(500, 2000)
            playableNote.duration = randomDuration
            melody.add(playableNote)      // or  melody += playableNote
            count += 1
        }
    }
    // Pattern: descend(4,3,2,1)
    private fun descend(melody: MutableList<Note>) {
        val scale = Scale()
        var selectedScale = scale.returnSelectedScale(_scalePickedLive.value!!)
        if (selectedScale.isNullOrEmpty()) {
            selectedScale = listOf(
                246.9417, 261.6256, 277.1826, 293.6648, 329.6276, 349.2282, 369.9944, 391.9954,
                415.3047, 440.0000, 466.1638, 493.8833, 523.2511
            )
        }
        Log.i("note", "descend() -> selectedScale = $selectedScale")
        val stretchOfNotes = (2..4).random()
        var count = 0
        var frequency = 0.0
        val playableNote = Note()
        playableNote.toneObject = PerfectTune()
        while (count < stretchOfNotes) {
            val newIndex = lastUsedIndex - 1
            lastUsedIndex = newIndex
            frequency = selectedScale[newIndex]
            playableNote.frequency = frequency
            val randomDuration = Random.nextInt(500, 2000)
            playableNote.duration = randomDuration
            melody.add(playableNote)
            count += 1
        }
    }
    // 3. 141 592 653 589 793 238 462 643 3
    // Pattern: pi (3,1,4,1,5,9,2,6,5,3,5,8,9,7,9,3,2,3,8,4,6)
    private fun pi(melody: MutableList<Note>) {
        val scale = Scale()
        var selectedScale = scale.returnSelectedScale(_scalePickedLive.value!!)
        if (selectedScale.isNullOrEmpty()) {
            selectedScale = listOf(
                246.9417, 261.6256, 277.1826, 293.6648, 329.6276, 349.2282, 369.9944, 391.9954,
                415.3047, 440.0000, 466.1638, 493.8833, 523.2511
            )
        }
        Log.i("note", "pi() -> selectedScale = $selectedScale")
        val stretchOfNotes = (2..21).random()
        var count = 0
        var frequency: Double
        val playableNote = Note()
        playableNote.toneObject = PerfectTune()
        val piValue = listOf(3,1,4,1,5,9,2,6,5,3,5,8,9,7,9,3,2,3,8,4,6)
        while (count < stretchOfNotes) {
            for (index in piValue) {
                val newIndex = piValue[index]
                lastUsedIndex = newIndex
                frequency = selectedScale[newIndex]
                playableNote.frequency = frequency
                val randomDuration = Random.nextInt(500, 2000)
                playableNote.duration = randomDuration
                melody.add(playableNote)
                count += 1
            }
        }
    }
    // Pattern: fibonacci (0,1,1,2,3,5,8)
    private fun fibonacci(melody: MutableList<Note>) {
        val scale = Scale()
        var selectedScale = scale.returnSelectedScale(_scalePickedLive.value!!)
        if (selectedScale.isNullOrEmpty()) {
            selectedScale = listOf(
                246.9417, 261.6256, 277.1826, 293.6648, 329.6276, 349.2282, 369.9944, 391.9954,
                415.3047, 440.0000, 466.1638, 493.8833, 523.2511
            )
        }
        Log.i("note", "fibonacci() -> selectedScale = $selectedScale")
        val stretchOfNotes = (2..6).random()
        var count = 0
        var frequency: Double
        val playableNote = Note()
        playableNote.toneObject = PerfectTune()
        val fibonacciValue = listOf(0,1,1,2,3,5)
        while (count < stretchOfNotes) {
            for (index in fibonacciValue) {
                val newIndex = fibonacciValue[index]
                lastUsedIndex = newIndex
                frequency = selectedScale[newIndex]
                playableNote.frequency = frequency
                val randomDuration = Random.nextInt(500, 2000)
                playableNote.duration = randomDuration
                melody.add(playableNote)
                count += 1
            }
        }
    }
    // Pattern: heartbeat (2,3,1,7,0,2,4,2)
    private fun heartbeat(melody: MutableList<Note>) {
        val scale = Scale()
        var selectedScale = scale.returnSelectedScale(_scalePickedLive.value!!)
        if (selectedScale.isNullOrEmpty()) {
            selectedScale = listOf(
                246.9417, 261.6256, 277.1826, 293.6648, 329.6276, 349.2282, 369.9944, 391.9954,
                415.3047, 440.0000, 466.1638, 493.8833, 523.2511
            )
        }
        Log.i("note", "heartbeat() -> selectedScale = $selectedScale")
        val stretchOfNotes = (2..8).random()
        var count = 0
        var frequency: Double
        val playableNote = Note()
        playableNote.toneObject = PerfectTune()
        val heartbeatValue = listOf(2,3,1,7,0,2,4,2)
        while (count < stretchOfNotes) {
            for (index in heartbeatValue) {
                val newIndex = heartbeatValue[index]
                lastUsedIndex = newIndex
                frequency = selectedScale[newIndex]
                playableNote.frequency = frequency
                val randomDuration = Random.nextInt(500, 2000)
                playableNote.duration = randomDuration
                melody.add(playableNote)
                count += 1
            }
        }
    }
    // Pattern: waltz (2,3,3,1,2,2)
    private fun waltz(melody: MutableList<Note>) {
        val scale = Scale()
        var selectedScale = scale.returnSelectedScale(_scalePickedLive.value!!)
        if (selectedScale.isNullOrEmpty()) {
            selectedScale = listOf(
                246.9417, 261.6256, 277.1826, 293.6648, 329.6276, 349.2282, 369.9944, 391.9954,
                415.3047, 440.0000, 466.1638, 493.8833, 523.2511
            )
        }
        Log.i("note", "waltz() -> selectedScale = $selectedScale")
        val stretchOfNotes = (2..12).random()
        var count = 0
        var frequency: Double
        val playableNote = Note()
        playableNote.toneObject = PerfectTune()
        var newIndex = 0
        while ((count < stretchOfNotes) && (newIndex < selectedScale.size)) {
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
            frequency = selectedScale[newIndex]
            playableNote.frequency = frequency
            val randomDuration = Random.nextInt(500, 2000)
            playableNote.duration = randomDuration
            melody.add(playableNote)
            count += 1
        }
    }
    // Pattern:  triad ascend (1,2,3)
    private fun ascendTriad(melody: MutableList<Note>) {
        val scale = Scale()
        var selectedScale = scale.returnSelectedScale(_scalePickedLive.value!!)
        if (selectedScale.isNullOrEmpty()) {
            selectedScale = listOf(
                246.9417, 261.6256, 277.1826, 293.6648, 329.6276, 349.2282, 369.9944, 391.9954,
                415.3047, 440.0000, 466.1638, 493.8833, 523.2511
            )
        }
        Log.i("note", "ascendTriad() -> selectedScale = $selectedScale")
        val stretchOfNotes = (3..6).random()
        var count = 0
        var frequency: Double
        val playableNote = Note()
        playableNote.toneObject = PerfectTune()
        var newIndex = 0
        while (count < stretchOfNotes && newIndex > selectedScale.size) {
            if (count % 3 == 0) {
                newIndex = lastUsedIndex + 0
                lastUsedIndex = newIndex
                frequency = selectedScale[newIndex]
            } else if (count % 3 == 1) {
                newIndex = lastUsedIndex + 1
                lastUsedIndex = newIndex
                frequency = selectedScale[newIndex]
            } else {
                newIndex = lastUsedIndex + 1
                lastUsedIndex = newIndex
                frequency = selectedScale[newIndex]
                val lowBoundry = lastUsedIndex - 4
                val highBoundry = lastUsedIndex + 1
                lastUsedIndex = (lowBoundry..highBoundry).random()
            }
            playableNote.frequency = frequency
            val randomDuration = Random.nextInt(500, 2000)
            playableNote.duration = randomDuration
            melody.add(playableNote)
            count += 1
        }
    }
    // Pattern: triad descend (3,2,1)
    private fun descendTriad(melody: MutableList<Note>) {
        val scale = Scale()
        var selectedScale = scale.returnSelectedScale(_scalePickedLive.value!!)
        if (selectedScale.isNullOrEmpty()) {
            selectedScale = listOf(
                246.9417, 261.6256, 277.1826, 293.6648, 329.6276, 349.2282, 369.9944, 391.9954,
                415.3047, 440.0000, 466.1638, 493.8833, 523.2511
            )
        }
        Log.i("note", "descendTriad() -> selectedScale = $selectedScale")
        val stretchOfNotes = (3..6).random()
        var count = 0
        var frequency: Double
        val playableNote = Note()
        playableNote.toneObject = PerfectTune()
        var newIndex: Int
        while (count < stretchOfNotes) {
            if (count % 3 == 0) {
                newIndex = lastUsedIndex + 0
                lastUsedIndex = newIndex
                frequency = selectedScale[newIndex]
            } else if (count % 3 == 1) {
                newIndex = lastUsedIndex - 1
                lastUsedIndex = newIndex
                frequency = selectedScale[newIndex]
            } else {
                newIndex = lastUsedIndex - 2
                lastUsedIndex = newIndex
                frequency = selectedScale[newIndex]
                lastUsedIndex += 3
            }
            playableNote.frequency = frequency
            val randomDuration = Random.nextInt(500, 2000)
            playableNote.duration = randomDuration
            melody.add(playableNote)
            count += 1
        }
    }
    // Pattern: pyramid (1,3,2)
    private fun pyramid(melody: MutableList<Note>) {
        val scale = Scale()
        var selectedScale = scale.returnSelectedScale(_scalePickedLive.value!!)
        if (selectedScale.isNullOrEmpty()) {
            selectedScale = listOf(
                246.9417, 261.6256, 277.1826, 293.6648, 329.6276, 349.2282, 369.9944, 391.9954,
                415.3047, 440.0000, 466.1638, 493.8833, 523.2511
            )
        }
        Log.i("note", "pyramid() -> selectedScale = $selectedScale")
        val stretchOfNotes = (3..6).random()
        var frequency: Double
        var count = 0
        val playableNote = Note()
        playableNote.toneObject = PerfectTune()
        var newIndex: Int
        while (count < stretchOfNotes) {
            if (count % 3 == 0) {
                newIndex = lastUsedIndex + 0
                lastUsedIndex = newIndex
                frequency = selectedScale[newIndex]
            } else if (count % 3 == 1) {
                newIndex = lastUsedIndex + 2
                lastUsedIndex = newIndex
                frequency = selectedScale[newIndex]
            } else {
                newIndex = lastUsedIndex - 1
                lastUsedIndex = newIndex
                frequency = selectedScale[newIndex]
                lastUsedIndex -= 1
            }
            playableNote.frequency = frequency
            val randomDuration = Random.nextInt(500, 2000)
            playableNote.duration = randomDuration
            melody.add(playableNote)
            count += 1
        }
    }
    // Pattern: deku (2,3,1)
    private fun deku(melody: MutableList<Note>) {
        val scale = Scale()
        var selectedScale = scale.returnSelectedScale(_scalePickedLive.value!!)
        if (selectedScale.isNullOrEmpty()) {
            selectedScale = listOf(
                246.9417, 261.6256, 277.1826, 293.6648, 329.6276, 349.2282, 369.9944, 391.9954,
                415.3047, 440.0000, 466.1638, 493.8833, 523.2511
            )
        }
        Log.i("note", "deku() -> selectedScale = $selectedScale")
        val stretchOfNotes = (3..6).random()
        var frequency: Double
        var count = 0
        val playableNote = Note()
        playableNote.toneObject = PerfectTune()
        var newIndex: Int
        while (count < stretchOfNotes) {
            if (count % 3 == 0) {
                newIndex = lastUsedIndex + 1
                lastUsedIndex = newIndex
                frequency = selectedScale[newIndex]
            } else if (count % 3 == 1) {
                newIndex = lastUsedIndex + 1
                lastUsedIndex = newIndex
                frequency = selectedScale[newIndex]
            } else {
                newIndex = lastUsedIndex - 2
                lastUsedIndex = newIndex
                frequency = selectedScale[newIndex]
                lastUsedIndex += 1
            }
            playableNote.frequency = frequency
            val randomDuration = Random.nextInt(500, 2000)
            playableNote.duration = randomDuration
            melody.add(playableNote)
            count += 1
        }
    }
    // Pattern: steps (1,1,2,2,3,3)
    private fun steps(melody: MutableList<Note>) {
        val scale = Scale()
        var selectedScale = scale.returnSelectedScale(_scalePickedLive.value!!)
        if (selectedScale.isNullOrEmpty()) {
            selectedScale = listOf(
                246.9417, 261.6256, 277.1826, 293.6648, 329.6276, 349.2282, 369.9944, 391.9954,
                415.3047, 440.0000, 466.1638, 493.8833, 523.2511
            )
        }
        Log.i("note", "steps() -> selectedScale = $selectedScale")
        val stretchOfNotes = (2..12).random()
        var frequency: Double
        var count = 0
        val playableNote = Note()
        playableNote.toneObject = PerfectTune()
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
            frequency = selectedScale[newIndex]
            playableNote.frequency = frequency
            val randomDuration = Random.nextInt(500, 2000)
            playableNote.duration = randomDuration
            melody.add(playableNote)
            count += 1
        }
    }
    // Pattern: skip (1,1,2,2,1,1,2,2)
    private fun skip(melody: MutableList<Note>) {
        val scale = Scale()
        var selectedScale = scale.returnSelectedScale(_scalePickedLive.value!!)
        if (selectedScale.isNullOrEmpty()) {
            selectedScale = listOf(
                246.9417, 261.6256, 277.1826, 293.6648, 329.6276, 349.2282, 369.9944, 391.9954,
                415.3047, 440.0000, 466.1638, 493.8833, 523.2511
            )
        }
        Log.i("note", "skip() -> selectedScale = $selectedScale")
        val stretchOfNotes = (2..12).random()
        var frequency: Double
        var count = 0
        val playableNote = Note()
        playableNote.toneObject = PerfectTune()
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
            frequency = selectedScale[newIndex]
            playableNote.frequency = frequency
            val randomDuration = Random.nextInt(500, 2000)
            playableNote.duration = randomDuration
            melody.add(playableNote)
            count += 1
        }
    }


    fun generateMelody(generatedList: TextView): MutableList<Note> {
        // Clear previous generated melody if it exists
        clearOutPreviouslyGeneratedMelody(generatedList)

        // Check that liveData is not null, if it is, assign defaults
        checkIfMelodyLengthIsNull()
        val scale = Scale()
        checkIfScaleIsNull(scale)
        checkIfMoodIsNull()

        // Placeholder for initial frequency
        var chosenMood = listOf<String>()

        // Moods defined
        val rocky = listOf("ascend","descend")
        val dangerous = listOf("fibonacci", "pi", "heartbeat")
        val lullaby = listOf("waltz")
        val soaring = listOf("ascend", "ascendTriad", "descend", "descendTriad", "deku", "pyramid", "steps")
        val rainyDay = listOf("skip")

        // Map/Dictionary of Moods
        val listOfMoods = mapOf(
            "Rocky" to rocky,
            "Dangerous" to dangerous,
            "Lullaby" to lullaby,
            "Soaring" to soaring,
            "Rainy Day" to rainyDay
        )

        // Map of patterns
        val listOfPatterns = mapOf(
            "ascend" to ascend(melody),
            "ascendTriad" to ascendTriad(melody),
            "descend" to descend(melody),
            "descendTriad" to descendTriad(melody),
            "deku" to deku(melody),
            "fibonacci" to fibonacci(melody),
            "heartbeat" to heartbeat(melody),
            "pi" to pi(melody),
            "pyramid" to pyramid(melody),
            "skip" to skip(melody),
            "steps" to steps(melody),
            "waltz" to waltz(melody)
        )

        // Assign mood picked (string) to mood listing patterns (list of Strings)
        for ((key, value) in listOfMoods) {
            if (key == moodPickedLive.value) {
                chosenMood = value
                Log.i("note", "inside generateMelody - Mood name = $key")
                Log.i("note", "inside generateMelody - Mood patterns = $chosenMood")
            }
        }

        // Empty Melody list
        melody = mutableListOf<Note>()

        // Check length of generated melody
        while (melody.size < countPickedLive.value!!) {
            // choose a random pattern from selected mood
            val selectedPattern = chosenMood.random()
            Log.i("note", "inside generateMelody() - selectedPattern name = $selectedPattern")
            // find pattern function related to selected pattern
            for ((key, value) in listOfPatterns) {
                if (key == selectedPattern) {
                    value   // pattern function
                    Log.i("note", "inside generateMelody() -> if reached - selectedPattern name = $key")
                    Log.i("note", "inside generateMelody() -> if reached - selectedPattern function = $value")
                    Log.i("note", "inside generateMelody() -> if reached - melody is now = $melody")
                }
            }
        }
        // If melody is longer than total notes selected, remove extra of notes
        if (melody.size > countPickedLive.value!!) {
            val howMuchOverTotalNotes = melody.size - countPickedLive.value!!
            while (howMuchOverTotalNotes > 0 && melody.size > 0) {
                Log.i("note", "inside generateMelody() -> removed from melody = ${melody.last()}")
                melody.removeLast()
                Log.i("note", "inside generateMelody() -> updated melody = $melody")
            }
        }
        updateMelodyTextViewDisplay()
        return melody
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
                Log.i("note", "playMelody - note frequency = ${melody[index].frequency}")
                melody[index].assignFrequency()
                melody[index].playFreq()
                startCoolDown(melody[index],1)
                index += 1
            }
            override fun onFinish() {
                return
            }
        }.start()
    }



    /**
     * Stop melody audio playback
     */
//    fun stopTune(note: Note) {
//            note.toneObject.stopTune()
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