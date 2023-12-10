import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

class Day7Tests {

    val INPUT = "32T3K 765\n" +
            "T55J5 684\n" +
            "KK677 28\n" +
            "KTJJT 220\n" +
            "QQQJA 483"
    @Test
    fun testHandKind() {
        assertEquals(HandType.FIVE_OF_A_KIND, Hand("AAAAA").type)
        assertEquals(HandType.FOUR_OF_A_KIND, Hand("AA8AA").type)
        assertEquals(HandType.FULL_HOUSE, Hand("23332").type)
        assertEquals(HandType.THREE_OF_A_KIND, Hand("TTT98").type)
        assertEquals(HandType.TWO_PAIR, Hand("23432").type)
        assertEquals(HandType.ONE_PAIR, Hand("A23A4").type)
        assertEquals(HandType.HIGH_CARD, Hand("23456").type)
    }

    @Test
    fun testHandCompare() {
        assertTrue(Hand("33332") > Hand("2AAAA"))
        assertTrue(Hand("77888") > Hand("77788"))
    }

    @Test
    fun testRank() {
        val hands = parseHands(INPUT)
        assertEquals(listOf(
            Hand("32T3K") to 765,
            Hand("KTJJT") to 220,
            Hand("KK677") to 28,
            Hand("T55J5") to 684,
            Hand("QQQJA") to 483
        ), hands)
    }

    @Test
    fun testWinnings() {
        val hands = parseHands(INPUT)
        assertEquals(6440, winnings(hands))
    }

    @Test
    fun testJokerKind() {
        assertEquals(HandType.ONE_PAIR, Hand("32T3K", jokers = true).type)
        assertEquals(HandType.TWO_PAIR, Hand("KK677", jokers = true).type)
        assertEquals(HandType.FOUR_OF_A_KIND, Hand("T55J5", jokers = true).type)
        assertEquals(HandType.FOUR_OF_A_KIND, Hand("KTJJT", jokers = true).type)
    }

    @Test
    fun testJokerRank() {
        val hands = parseHands(INPUT, jokers = true)
        assertTrue(Hand("JJJJJ", jokers = true) < Hand("22222"))
        assertEquals(HandType.FOUR_OF_A_KIND, Hand("T55J5", jokers = true).type)
        assertEquals(listOf(
            "32T3K",
            "KK677",
            "T55J5",
            "QQQJA",
            "KTJJT",
        ), hands.map { it.first.cards })
        assertEquals(5905, winnings(hands))
    }
}
