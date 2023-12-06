import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

class Day5Tests {

    val TEST_INPUT =
                "seeds: 79 14 55 13\n" +
                "\n" +
                "seed-to-soil map:\n" +
                "50 98 2\n" +
                "52 50 48\n" +
                "\n" +
                "soil-to-fertilizer map:\n" +
                "0 15 37\n" +
                "37 52 2\n" +
                "39 0 15\n" +
                "\n" +
                "fertilizer-to-water map:\n" +
                "49 53 8\n" +
                "0 11 42\n" +
                "42 0 7\n" +
                "57 7 4\n" +
                "\n" +
                "water-to-light map:\n" +
                "88 18 7\n" +
                "18 25 70\n" +
                "\n" +
                "light-to-temperature map:\n" +
                "45 77 23\n" +
                "81 45 19\n" +
                "68 64 13\n" +
                "\n" +
                "temperature-to-humidity map:\n" +
                "0 69 1\n" +
                "1 0 69\n" +
                "\n" +
                "humidity-to-location map:\n" +
                "60 56 37\n" +
                "56 93 4"

    val almanac = Almanac.parse(TEST_INPUT)

    @Test
    fun testParseAlmanac() {
        assertEquals(listOf(79, 14, 55, 13), almanac.seeds)
        assertEquals(7, almanac.maps.size)
        assertEquals(mapOf(
            98..99 to 50..51,
            50..97 to 52..99
            ),
            almanac.maps.first()
        )
    }

    @Test
    fun testLookup() {
        val seedToSoil = almanac.maps.first()
        assertEquals(listOf(81, 14, 57, 13), almanac.seeds.map { almanac.lookup(it, seedToSoil) })
        assertEquals(listOf(82, 43, 86, 35), almanac.lookup())
    }

    @Test
    fun testTotalCards() {
        assertEquals(30, totalCards(cards))
    }

}
