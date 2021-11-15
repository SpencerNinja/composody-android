package com.example.composody.data

class Patterns {

    // Number of Notes selected
    var totalNotes = 0

    // Which scale did the user select
    val selectedScale = listOf(174.6141,195.9977,220.0000,261.6256,293.6648,349.2282,391.9954,440.0000,523.2511,587.3295,783.9909,880.0000,1046.5020)

    // Chosen Mood
    val chosenMood = listOf(listOf<Double>())

    var lastUsedIndex = selectedScale.size / 2


    /**
     * Patterns
     */
    // Ascend(1,2,3,4)
    // Descend(4,3,2,1)

    // Pi (3,1,4,1,5,9)
    // Fibonacci (0,1,1,2,3,5,8)
    // Heartbeat (2,3,1,7,0,2,4,2)
    // Waltz (2,3,3,1,2,2)
    // Triad Ascend (1,2,3)
    // Triad Descend (3,2,1)
    // Pyramid (1,3,2)
    // Deku (2,3,1)
    // Steps (1,1,2,2,3,3)
    // Skip (1,1,2,2,1,1,2,2)

    // NOT USED (handled by other patterns)
        // Mountain (1,2,3,4,3,2,3,4)
        // Switchback (1,2,3,4,3,2,1,2,3,2,1,2,1)

    // Ascend(1,2,3,4)
    private fun ascend(melody: MutableList<Double>): List<Double> {
        val stretchOfNotes = (2..4).random()
        var note = checkForLastGeneratedNote(melody)
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
        val stretchOfNotes = (2..4).random()
        var note = checkForLastGeneratedNote(melody)
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
        val stretchOfNotes = (2..21).random()
        var note = checkForLastGeneratedNote(melody)
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
        val stretchOfNotes = (2..7).random()
        var note = checkForLastGeneratedNote(melody)
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
        val stretchOfNotes = (2..8).random()
        var note = checkForLastGeneratedNote(melody)
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
        val stretchOfNotes = (2..12).random()
        var note = checkForLastGeneratedNote(melody)
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
                newIndex =
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
        return melody
    }
    // Triad Descend (3,2,1)
    private fun descendTriad(melody: MutableList<Double>): List<Double> {
        return melody
    }
    // Pyramid (1,3,2)
    private fun pyramid(melody: MutableList<Double>): List<Double> {
        return melody
    }
    // Deku (2,3,1)
    private fun deku(melody: MutableList<Double>): List<Double> {
        return melody
    }
    // Steps (1,1,2,2,3,3)
    private fun steps(melody: MutableList<Double>): List<Double> {
        return melody
    }
    // Skip (1,1,2,2,1,1,2,2)
    private fun skip(melody: MutableList<Double>): List<Double> {
        return melody
    }


    /**
     * Moods
     */
    // Rocky = ascend, descend
    // Dangerous = fibonacci, pi, heartbeat
    // Lullaby = waltz
    // Soaring = pyramid, triad ascend, triad descend, deku, steps
    // Rainy Day = skip


    /**
     * Functions
     */
    private fun checkForLastGeneratedNote(melody: MutableList<Double>): Double {
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

    private fun randomlySelectPattern(){
        var randomlySelectedPattern = chosenMood.random()
    }

    private fun checkMelodyLength(melody: MutableList<Double>) {
        while (melody.size < totalNotes) {
             randomlySelectPattern()
        }
        if (melody.size > totalNotes) {
            val howMuchOverTotalNotes = melody.size - totalNotes
            // remove (melody.size - totalNotes) from end of melody
            while (howMuchOverTotalNotes > 0) {
                melody.removeLast()
            }
        }
    }

    fun generateMelodyBasedOnSelectedMood(totalNotes: Int, selectedScale: List<Double>, moodPicked: List<List<Double>>): List<Double> {
        var melody = mutableListOf<Double>()

        val rocky = listOf<List<Double>>(ascend(melody),descend(melody))
        val dangerous = listOf<List<Double>>(fibonacci(melody), pi(melody), heartbeat(melody))
        val lullaby = listOf<List<Double>>(waltz(melody))
        val soaring = listOf<List<Double>>(ascend(melody), ascendTriad(melody), descend(melody), descendTriad(melody), deku(melody), pyramid(melody), steps(melody))
        val rainyDay = listOf<List<Double>>(skip(melody))

        checkMelodyLength(melody)
        return melody
    }

}

/**
 * PROCESS TO RETURN A MELODY BUILT ON PATTERNS
 * 1. User selects totalNotes, selectedScale, and chosenMood
 * 2. Create an empty list to store melody (generatedMelody)
 * 3. generateMelody function takes in totalNotes, selectedScale, and chosenMood
 *    4. While size of generatedMelody is less than totalNotes
 *       5. Randomly select a pattern from chosenMood
 *       6. Add a few notes to melody list in a pattern
 *    7. If generatedMelody.size > totalNotes
 *       8. difference = generatedMelody.size - totalNotes
 *       9. Remove difference amount of items from the end of the generatedMelody
 *    10. Return generatedMelody
 */


