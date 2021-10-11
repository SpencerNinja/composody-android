package com.example.testaudio

import com.karlotoy.perfectune.instance.PerfectTune
import kotlin.random.Random

class Note() {
    var frequency: Double
    var toneObject: PerfectTune
    var duration: Int

    init {
        frequency = 0.0
        toneObject = PerfectTune()
        duration = 1000
    }

    fun assignDuration() {
        var randomDuration = Random.nextInt(500, 2000)
        duration = randomDuration
    }

    fun assignFrequency() {
        toneObject.tuneFreq = frequency
    }

    fun playFreq() {
        toneObject.tuneAmplitude = 10000
        toneObject.playTune()
    }

}