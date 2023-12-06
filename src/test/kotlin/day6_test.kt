import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

class Day6Tests {

    val TEST_INPUT =
                "Time:      7  15   30\n" +
                "Distance:  9  40  200"

    val races = parseRaces(TEST_INPUT)

    @Test
    fun testParseRaces() {
        assertEquals(3, races.size)
        assertEquals(Race(7, 9), races.first())
    }

    @Test
    fun testFindWins() {
        assertEquals(listOf(2L, 3L, 4L, 5L), findWins(races.first()))
        assertEquals(listOf(4, 8, 9), races.map { findWins(it).size })
    }

    @Test
    fun testPart2() {
        val race = parseRaces("Time:      71530\nDistance:  940200").first()
        assertEquals(71503, findWins(race).size)
    }

}
