import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

class Day3Tests {

    val TEST_INPUT =
        "467..114..\n" +
        "...*......\n" +
        "..35..633.\n" +
        "......#...\n" +
        "617*......\n" +
        ".....+.58.\n" +
        "..592.....\n" +
        "......755.\n" +
        "...\$.*....\n" +
        ".664.598.."

    val schematic = Engine.parse(TEST_INPUT)

    @Test
    fun testParseParts() {
        assertEquals(schematic.parts, listOf(467, 35, 633, 617, 592, 755, 664, 598))
    }

    @Test
    fun testParseGears() {
        assertEquals(schematic.gears, listOf(16345, 451490))
    }

}
