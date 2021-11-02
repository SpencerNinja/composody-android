package com.example.composody

import android.util.Log
import kotlin.random.Random

class Scale {

    // Create a variable of each scale and the frequencies of the notes in that scale
    var allNotes = listOf(246.9417, 261.6256, 277.1826, 293.6648, 329.6276, 349.2282, 369.9944, 391.9954, 415.3047, 440.0000, 466.1638, 493.8833, 523.2511)
    var acoustic = listOf(233.0819, 261.6256, 293.6648, 329.6276, 369.9944, 391.9954, 440.0000, 466.1638, 523.2511, 587.3295, 659.2551, 739.9888, 783.9909)
    var aeolianMode = listOf(233.0819, 261.6256, 293.6648, 311.1270, 349.2282, 391.9954, 415.3047, 466.1638, 523.2511, 587.3295, 622.2540, 698.4565, 783.9909)
    var algerian = listOf(246.9417, 261.6256, 293.6648, 311.1270, 369.9944, 391.9954, 415.3047, 493.8833, 523.2511, 587.3295, 622.2540, 698.4565, 783.9909)
    var augmented = listOf(246.9417, 261.6256, 311.1270, 329.6276, 391.9954, 415.3047, 493.8833, 523.2511, 622.2540, 659.2551, 783.9909, 830.6094, 987.7666)
    var bebopDominant = listOf(246.9417, 261.6256, 293.6648, 329.6276, 349.2282, 391.9954, 440.0000, 466.1638, 493.8833, 523.2511, 587.3295, 659.2551, 698.4565)
    var blues = listOf(233.0819, 261.6256, 311.1270, 349.2282, 369.9944, 391.9954, 466.1638, 523.2511, 622.2540, 698.4565, 739.9888, 783.9909, 932.3275)
    var chromatic = listOf(261.6256, 277.1826, 293.6648, 311.1270, 329.6276, 349.2282, 369.9944, 391.9954, 415.3047, 440.0000, 466.1638, 493.8833, 523.2511)
    var dorianMode = listOf(261.6256, 293.6648, 311.1270, 349.2282, 391.9954, 440.0000, 466.1638, 523.2511, 587.3295, 622.2540, 698.4565, 783.9909, 880.0000)
    var enigmatic = listOf(261.6256, 277.1826, 329.6276, 369.9944, 415.3047, 466.1638, 493.8833, 523.2511, 554.3653, 659.2551, 739.9888, 830.6094, 932.3275)
    var flamencoMode = listOf(261.6256,277.1826,329.6276,349.2282,391.9954,415.3047,493.8833,523.2511,554.3653,659.2551,698.4565,783.9909,830.6094)
    var gypsy = listOf(261.6256,293.6648,311.1270,369.9944,391.9954,415.3047,466.1638,523.2511,587.3295,622.2540,739.9888,783.9909,830.6094)
    var harmonicMajor = listOf(261.6256,293.6648,329.6276,349.2282,391.9954,415.3047,493.8833,523.2511,587.3295,659.2551,698.4565,783.9909,830.6094)
    var harmonicMinor = listOf(261.6256,293.6648,311.1270,349.2282,391.9954,415.3047,493.8833,523.2511,587.3295,622.2540,698.4565,783.9909,830.6094)
    var hirajoshi = listOf(195.9977,246.9417,261.6256,329.6276,369.9944,391.9954,493.8833,523.2511,659.2551,739.9888,783.9909,987.7666,1046.5020)
    var hungarianMajor = listOf(261.6256,311.1270,329.6276,369.9944,391.9954,440.0000,466.1638,523.2511,622.2540,659.2551,739.9888,783.9909,880.0000)
    var insen = listOf(195.9977,233.0819,261.6256,277.1826,349.2282,391.9954,466.1638,523.2511,554.3653,698.4565,783.9909,932.3275,1046.5020)
    var ionianMode = listOf(261.6256,293.6648,329.6276,349.2282,391.9954,440.0000,493.8833,523.2511,587.3295,659.2551,698.4565,783.9909,880.0000)
    var iwato = listOf(184.9972,233.0819,261.6256,277.1826,349.2282,369.9944,466.1638,523.2511,554.3653,698.4565,739.9888,932.3275,1046.5020)
    var locrianMode = listOf(261.6256,277.1826,311.1270,349.2282,369.9944,415.3047,466.1638,523.2511,554.3653,622.2540,698.4565,739.9888,830.6094)
    var lydianMode = listOf(261.6256,293.6648,329.6276,369.9944,391.9954,440.0000,493.8833,523.2511,587.3295,659.2551,739.9888,783.9909,880.0000)
    var majorBebop = listOf(261.6256,293.6648,329.6276,349.2282,391.9954,415.3047,440.0000,493.8833,523.2511,587.3295,659.2551,698.4565,783.9909)
    var majorLocrian = listOf(261.6256,293.6648,329.6276,349.2282,369.9944,415.3047,466.1638,523.2511,587.3295,659.2551,698.4565,739.9888,830.6094)
    var majorPentatonic = listOf(195.9977,220.0000,261.6256,293.6648,311.1270,391.9954,440.0000,523.2511,587.3295,659.2551,783.9909,880.0000,1046.5020)
    var minorPentatonic = listOf(195.9977,220.0000,261.6256,293.6648,329.6276,391.9954,440.0000,523.2511,587.3295,659.2551,783.9909,880.0000,1046.5020)
    var mixolydianMode = listOf(261.6256,293.6648,329.6276,349.2282,391.9954,440.0000,466.1638,523.2511,587.3295,659.2551,698.4565,783.9909,880.0000)
    var neapolitanMajor = listOf(261.6256,277.1826,311.1270,349.2282,391.9954,440.0000,493.8833,523.2511,554.3653,622.2540,698.4565,783.9909,880.0000)
    var neapolitanMinor = listOf(246.9417,277.1826,311.1270,349.2282,391.9954,415.3047,493.8833,523.2511,554.3653,622.2540,698.4565,783.9909,830.6094)
    var octatonic = listOf(261.6256,277.1826,311.1270,329.6276,369.9944,391.9954,440.0000,466.1638,523.2511,554.3653,622.2540,659.2551,739.9888)
    var pelog = listOf(293.6648,311.1270,349.2282,415.3047,440.0000,466.1638,523.2511,587.3295,622.2540,698.4565,830.6094,880.0000,932.3275)
    var persian = listOf(261.6256,277.1826,329.6276,349.2282,369.9944,415.3047,493.8833,523.2511,554.3653,659.2551,698.4565,739.9888,830.6094)
    var phrygianDominant = listOf(261.6256,277.1826,329.6276,349.2282,391.9954,415.3047,466.1638,523.2511,554.3653,659.2551,698.4565,783.9909,830.6094)
    var phrygianMode = listOf(261.6256,277.1826,311.1270,349.2282,391.9954,415.3047,466.1638,523.2511,554.3653,622.2540,698.4565,783.9909,830.6094)
    var prometheus = listOf(261.6256,293.6648,329.6276,369.9944,440.0000,466.1638,523.2511,587.3295,659.2551,739.9888,880.0000,932.3275,1046.5020)
    var shadow = listOf(184.9972,207.6523,233.0819,277.1826,311.1270,369.9944,415.3047,466.1638,554.3653,622.2540,739.9888,830.6094,932.3275)
    var slendro = listOf(261.6256,293.6648,329.6276,369.9944,415.3047,466.1638,523.2511,587.3295,659.2551,739.9888,830.6094,932.3275,1046.5020)
    var tritone = listOf(261.6256,277.1826,329.6276,369.9944,391.9954,466.1638,523.2511,554.3653,659.2551,739.9888,783.9909,932.3275,1046.5020)
    var ukrainianDorian = listOf(261.6256,293.6648,311.1270,369.9944,391.9954,440.0000,466.1638,523.2511,587.3295,622.2540,739.9888,783.9909,880.0000)
    var wholeTone = listOf(261.6256,293.6648,329.6276,369.9944,415.3047,466.1638,523.2511,587.3295,659.2551,739.9888,830.6094,932.3275,1046.5020)
    var yo = listOf(174.6141,195.9977,220.0000,261.6256,293.6648,349.2282,391.9954,440.0000,523.2511,587.3295,783.9909,880.0000,1046.5020)

    // Create a list of the available scale frequencies
    var listOfScales = listOf(allNotes, acoustic, aeolianMode, algerian, augmented, bebopDominant,
        blues, chromatic, dorianMode, enigmatic, flamencoMode, gypsy, harmonicMajor, harmonicMinor,
        hirajoshi, hungarianMajor, insen, ionianMode, iwato, locrianMode, lydianMode, majorBebop,
        majorLocrian, majorPentatonic, minorPentatonic, mixolydianMode, neapolitanMajor,
        neapolitanMinor, octatonic, pelog, persian, phrygianDominant, phrygianMode, prometheus,
        shadow, slendro, tritone, ukrainianDorian, wholeTone, yo)

    // Create a dictionary of scale names and frequency values
    var dictionaryOfScales = mapOf("All Notes" to allNotes, "Acoustic" to acoustic,
        "Aeolian Mode" to aeolianMode, "Algerian" to algerian, "Augmented" to augmented,
        "Bebop Dominant" to bebopDominant, "Blues" to blues, "Chromatic" to chromatic,
        "Dorian Mode" to dorianMode, "Enigmatic" to enigmatic, "Flamenco Mode" to flamencoMode,
        "Gypsy" to gypsy, "Harmonic Major" to harmonicMajor, "Harmonic Minor" to harmonicMinor,
        "Hirajoshi" to hirajoshi, "Hungarian Major" to hungarianMajor, "Insen" to insen,
        "Ionian Mode" to ionianMode, "Iwato" to iwato, "Locrian Mode" to locrianMode,
        "Lydian Mode" to lydianMode, "Major Bebop" to majorBebop, "Major Locrian" to majorLocrian,
        "Major Pentatonic" to majorPentatonic, "Minor Pentatonic" to minorPentatonic,
        "Mixolydian Mode" to mixolydianMode, "Neapolitan Major" to neapolitanMajor,
        "Neapolitan Minor" to neapolitanMinor, "Octatonic" to octatonic, "Pelog" to pelog,
        "Persian" to persian, "Phrygian Dominant" to phrygianDominant,
        "Phrygian Mode" to phrygianMode, "Prometheus" to prometheus, "Shadow" to shadow,
        "Slendro" to slendro, "Tritone" to tritone, "Ukrainian Dorian" to ukrainianDorian,
        "Whole Tone" to wholeTone, "Yo" to yo)

    // Create a list of Scale names
    fun returnListOfScaleNames(): List<String> {
        var listOfScaleNames = mutableListOf<String>()
        for ((key, value) in dictionaryOfScales) {
            listOfScaleNames.add(key)
        }
        return listOfScaleNames
    }

    // Return the user selected scale
    fun returnSelectedScale(scaleName: String): List<Double> {
        var scaleNoteFrequencies = listOf<Double>()
        for ((key, value) in dictionaryOfScales) {
            if (key == scaleName) {
                scaleNoteFrequencies = value
                Log.i("note", "returnSelectedScale - selected scale name = $key")
                Log.i("note", "returnSelectedScale - scale frequencies = $scaleNoteFrequencies")
            }
        }
        return scaleNoteFrequencies
    }

    // Choose a random scale from the list of scales
    fun selectRandomScale(): List<Double> {
        var randomScaleIndex = Random.nextInt(listOfScales.size)
        var randomScale = listOfScales[randomScaleIndex]
//        Log.i("note", "randomIndex = $randomScaleIndex")
//        Log.i("note", "randomScale = $randomScale")
        return randomScale
    }

}

