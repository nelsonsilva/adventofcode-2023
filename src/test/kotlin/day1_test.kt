import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

val TEST_INPUT = listOf("1abc2", "pqr3stu8vwx", "a1b2c3d4e5f", "treb7uchet")

val TEST_INPUT_2 = listOf(
    "two1nine",
    "eightwothree",
    "abcone2threexyz",
    "xtwone3four",
    "4nineeightseven2",
    "zoneight234",
    "7pqrstsixteen",
)

class Day1Tests {
    @Test
    fun getCalibrationValues() {
        assertEquals(getCalibrationValues(TEST_INPUT), listOf(12, 38, 15, 77))
    }

    @Test
    fun testFindNumbers() {
        assertEquals(findNumbers("eightwothree"), "823")
    }

    @Test
    fun testFixedCalibration() {
        val fixed = TEST_INPUT_2.map(::findNumbers)
        assertEquals(getCalibrationValues(fixed), listOf(29, 83, 13, 24, 42, 14, 76))
    }
}
