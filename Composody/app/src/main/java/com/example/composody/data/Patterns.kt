package com.example.composody.data

class Patterns {

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


//    var listOfPatterns = listOf(ascend(melody), ascendTriad(melody), descend(melody),
//        descendTriad(melody), deku(melody), fibonacci(melody), heartbeat(melody), pyramid(melody),
//        pi(melody), skip(melody), steps(melody),  waltz(melody))


    /**
     * Moods
     */
    // Rocky = ascend, descend
    // Dangerous = fibonacci, pi, heartbeat
    // Lullaby = waltz
    // Soaring = pyramid, triad ascend, triad descend, deku, steps
    // Rainy Day = skip

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


