package com.example.testaudio

import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.karlotoy.perfectune.instance.PerfectTune
import android.view.View
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    // How many notes
    var melodyLength = 7
    // Create an empty list to later store notes
    var notes = mutableListOf<Note>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        createMelody()
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
    fun stopTune(view: View) {
        if (view.id == R.id.button_sound_stop) {
            //stops the tune
//            note.toneObject.stopTune()
        }
    }

}
