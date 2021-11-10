package com.example.composody.data

class Patterns {

    /**
     * Moods
     */
    val rocky = listOf<List<Int>>()
    val dangerous = listOf<List<Int>>()
    val lullaby = listOf<List<Int>>()
    val soaring = listOf<List<Int>>()
    val rainyDay = listOf<List<Int>>()

    /**
     * Patterns
     */
    val triadAscend = listOf(1,2,3)
    val triadDescend = listOf(3,2,1)
    val climb = listOf(1,2,3,4,5,6)
    val descend = listOf(6,5,4,3,2,1)
    val mountain = listOf(1,2,3,4,3,2,3,4)
    val switchback = listOf<Int>(1,2,3,2,1)
    val pi = listOf(3,1,4,1,5,9)
    val fibonacci = listOf(0,1,1,2,3,5,8)
    val steps = listOf(1,1,2,2,3,3)
    val waltz = listOf(1,2,1,2,1,2)
    val skip = listOf(1,1,2,2,1,1,2,2)
    val heartbeat = listOf(2,3,1,7,0,2,4,2)

    /**
     * Formulas
     */
    // add 1 to index
    // add 2 to index
    // add 3 to index
    // add 4 to index
    // subtract 1 to index
    // subtract 2 to index
    // subtract 3 to index
    // subtract 4 to index
    // minimum val of index
    // maximum val of index
    // median val of index
    // modulus of _____

}

/**
 * Shifting between patterns
 *
 * 1. Start with a randomly selected note in the user selected scale
 * 2. Jump across the scale using the 1st selected number pattern
 * 3. Transition to the 2nd number pattern usin the last note ended on as a reference point to
 *    start the 2nd number sequence
 */
