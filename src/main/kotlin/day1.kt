// Part 1
fun getCalibrationValues(input: List<String>): List<Int> {
    return input.map { l ->
        val digits = l.filter(Char::isDigit)
        charArrayOf(digits.first(), digits.last()).concatToString().toInt()
    }
}

// Part 2
val NUMBERS = mapOf(
    "one" to 1,
    "two" to 2,
    "three" to 3,
    "four" to 4,
    "five" to 5,
    "six" to 6,
    "seven" to 7,
    "eight" to 8,
    "nine" to 9
)

fun findNumbers(line: String): String {
    var res = ""
    line.forEachIndexed { idx, c ->
        if (c.isDigit()) {
            res += c
        } else {
            NUMBERS.forEach { (k, v) ->
                if (line.substring(idx).startsWith(k)) {
                    res += v.toString()
                }
            }
        }
    }
    return res
}

val INPUT = readInput("day1").split("\n")

fun main() {
    // Part 1
    println(getCalibrationValues(INPUT).sum())
    // Part 2
    println(getCalibrationValues(INPUT.map(::findNumbers)).sum())
}
