package com.example.testaudio

import android.util.Log
import kotlin.random.Random

class Scale {

    // Create a variable of each scale and the frequencies of the notes in that scale
    var cMajor = listOf(261.6256, 293.6648, 293.6648, 349.2282, 391.9954, 440.0000, 493.8833, 523.2511)
    var pentatonic = listOf(261.6256, 293.6648, 293.6648, 391.9954, 440.0000)
    var pentatonic2	= listOf(220.0000, 261.6256, 293.6648, 293.6648, 391.9954)
    var aMinor = listOf(220.0000, 246.9417, 261.6256, 293.6648, 293.6648, 349.2282, 391.9954, 440.0000)
    var cMajorPentatonic = listOf(261.6256, 293.6648, 293.6648, 391.9954, 440.0000)
    var aMinorPentatonic = listOf(220.0000, 261.6256, 293.6648, 293.6648, 391.9954)
    var blues = listOf(220.0000, 261.6256, 293.6648, 311.1270, 293.6648, 391.9954, 440.0000)
    var harmonicMinor = listOf(261.6256, 293.6648, 311.1270, 349.2282, 391.9954, 415.3047, 493.8833, 523.2511)
    var alteredDominant = listOf(261.6256, 277.1826, 311.1270, 329.6276, 369.9944, 415.3047, 415.3047)
    var flamenco = listOf(261.6256, 277.1826, 293.6648, 349.2282, 391.9954, 415.3047, 493.8833, 523.2511)
    var hungarianMinor = listOf(261.6256, 293.6648, 311.1270, 369.9944, 415.3047, 493.8833)
    var persian = listOf(261.6256, 277.1826, 293.6648, 349.2282, 369.9944, 415.3047, 493.8833, 523.2511)
    var spanish = listOf(261.6256, 277.1826, 293.6648, 349.2282, 391.9954, 415.3047, 466.1638, 523.2511)
    var japanese = listOf(261.6256, 277.1826, 349.2282, 391.9954, 466.1638, 523.2511)
    var random = listOf(207.6523, 220.0000, 233.0819, 246.9417, 261.6256, 277.1826, 293.6648, 311.1270, 293.6648, 349.2282, 369.9944, 391.9954)

    // Create a list of the available scales
    var listOfScales = listOf(cMajor, pentatonic, pentatonic2, aMinor, cMajorPentatonic,
        aMinorPentatonic, blues, harmonicMinor, alteredDominant, flamenco, hungarianMinor, persian,
        spanish, japanese, random)

    // Choose a random scale from the list of scales
    fun selectRandomScale(): List<Double> {
        var randomScaleIndex = Random.nextInt(listOfScales.size)
        var randomScale = listOfScales[randomScaleIndex]
//        Log.i("note", "randomIndex = ${randomScaleIndex}")
//        Log.i("note", "randomScale = ${randomScale}")
        return randomScale
    }

}

//var cMajor = listOf("C4", "D4", "E4", "F4", "G4", "A4", "B4", "C5")
//var pentatonic = listOf("C4", "D4", "E4", "G4", "A4")
//var pentatonic2	= listOf("A3", "C4", "D4", "E4", "G4")
//var aMinor = listOf("A3", "B3", "C4", "D4", "E4", "F4", "G4", "A4")
//var cMajorPentatonic = listOf("C4", "D4", "E4", "G4", "A4")
//var aMinorPentatonic = listOf("A3", "C4", "D4", "E4", "G4")
//var blues = listOf("A3", "C4", "D4", "Eb4", "E4", "G4", "A4")
//var harmonicMinor = listOf("C4", "D4", "Eb4", "F4", "G4", "Ab4", "B4", "C5")
//var alteredDominant = listOf("C4", "Db4", "Eb4", "E4", "Gb4", "Ab4", "Bb4")
//var flamenco = listOf("C4", "Db4", "E4", "F4", "G4", "Ab4", "B4", "C5")
//var hungarianMinor = listOf("C4", "D4", "Eb4", "Gb4", "G4", "Ab4", "B4")
//var persian = listOf("C4", "Db4", "E4", "F4", "Gb4", "Ab4", "B4", "C5")
//var spanish = listOf("C4", "Db4", "E4", "F4", "G4", "Ab4", "Bb4", "C5")
//var japanese = listOf("C4", "Db4", "F4", "G4", "Bb4", "C5")
//var random = listOf("Ab3", "A3", "Bb3", "B3", "C4", "Db4", "D4", "Eb4", "E4", "F4", "Gb4", "G4")