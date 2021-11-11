package com.example.composody.data

class Patterns {

    /**
     * Moods
     */
    // ROCKY: Ascend, Descend,
    val rocky = listOf<List<Double>>(ascend(),descend())
    val dangerous = listOf<List<Double>>()
    val lullaby = listOf<List<Double>>()
    val soaring = listOf<List<Double>>(ascend())
    val rainyDay = listOf<List<Double>>()

    /**
     * Patterns
     */
    // Ascend(1,2,3,4)
    // Descend(4,3,2,1)

    // Mountain (1,2,3,4,3,2,3,4)
    // Switchback (1,2,3,4,3,2,1,2,3,2,1,2,1),
    // Triad Ascend (1,2,3,4)
    // Triad Descend (4,3,2,1)
    // Pi (3,1,4,1,5,9)
    // Fibonacci (0,1,1,2,3,5,8)
    // Steps (1,1,2,2,3,3)
    // Waltz (1,2,1,2,1,2)
    // Skip (1,1,2,2,1,1,2,2)
    // Heartbeat (2,3,1,7,0,2,4,2)

    /**
     * Pattern Formulas
     */
    // Ascend = listOf(1,2,3,4,5,6)
    fun ascend(): List<Double> {
        // Create an empty list to store the collection of notes
        var melody = mutableListOf<Double>()
        // How many notes will this pattern generate
        val stretchOfNotes = (2..4).random()
        // Which scale did the user select
        val selectedScale = listOf(174.6141,195.9977,220.0000,261.6256,293.6648,349.2282,391.9954,440.0000,523.2511,587.3295,783.9909,880.0000,1046.5020)
        // Middle index of the selected scale
        val middleIndex = selectedScale.size / 2
        // Placeholder for note
        var note = selectedScale[middleIndex]
        // Count to keep track of how many notes the pattern has added to the melody
        var count = 1
        while (count != stretchOfNotes) {
            // Increment the index
            val newIndex = middleIndex + 1
            // Note is now equal to frequency located at newIndex in selectedScale
            note = selectedScale[newIndex]
            // Add note to melody
            melody.add(note)
            // Increment count
            count += 1
        }
        return melody
    }
    // Descend = listOf(6,5,4,3,2,1)
    fun descend(): List<Double> {
        var melody = mutableListOf<Double>()
        val stretchOfNotes = (2..4).random()
        val selectedScale = listOf(174.6141,195.9977,220.0000,261.6256,293.6648,349.2282,391.9954,440.0000,523.2511,587.3295,783.9909,880.0000,1046.5020)
        val middleIndex = selectedScale.size / 2
        var note = selectedScale[middleIndex]
        var count = 1
        while (count != stretchOfNotes) {
            val newIndex = middleIndex - 1
            note = selectedScale[newIndex]
            melody.add(note)
            count += 1
        }
        return melody
    }

    fun randomlySelectPattern(moodPicked: List<List<Double>>) {}

    fun generateMelodyBasedOnSelectedMood(totalNotes: Int, selectedScale: List<Double>, moodPicked: List<List<Double>>) {}

}

/**
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


