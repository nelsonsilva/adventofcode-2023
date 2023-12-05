import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

class Day4Tests {

    val TEST_INPUT =
                "Card 1: 41 48 83 86 17 | 83 86  6 31 17  9 48 53\n" +
                "Card 2: 13 32 20 16 61 | 61 30 68 82 17 32 24 19\n" +
                "Card 3:  1 21 53 59 44 | 69 82 63 72 16 21 14  1\n" +
                "Card 4: 41 92 73 84 69 | 59 84 76 51 58  5 54 83\n" +
                "Card 5: 87 83 26 28 32 | 88 30 70 12 93 22 82 36\n" +
                "Card 6: 31 18 13 56 72 | 74 77 10 23 35 67 36 11"

    val cards = TEST_INPUT.split("\n").map(Card::parse)

    @Test
    fun testParseCards() {
        assertEquals(Card(1, listOf(41, 48, 83, 86, 17), listOf(83, 86, 6, 31, 17,  9, 48, 53)), cards.first())
    }

    @Test
    fun testCardPoints() {
        assertEquals(listOf(8, 2, 2, 1, 0, 0), cards.map { it.points })
    }

    @Test
    fun testtotalCards() {
        assertEquals(30, totalCards(cards))
    }

}
