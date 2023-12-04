import kotlin.math.absoluteValue

data class Pos(val row: Int, val col: Int)

data class Engine(val parts: List<Int>, val gears: List<Int>) {
    companion object {
        fun parse(schematic: String): Engine {

            val lines = schematic.split("\n")
            val ncols = lines[0].length

            // find symbols and engines
            val gearsCandidates = mutableListOf<Pos>()
            val symbols = lines.flatMapIndexed { row, line ->
                line.mapIndexedNotNull { col, c ->
                    if (!c.isDigit() && c != '.') {
                        if (c == '*') {
                            gearsCandidates.add(Pos(row, col))
                        }
                        Pos(row, col)
                    } else {
                        null
                    }
                }
            }

            fun findAdjacent(row: Int, cols: IntRange, pos: List<Pos>): List<Pos> {
                val range = IntRange(
                    (cols.first - 1).coerceAtLeast(0),
                    (cols.last + 1).coerceAtMost(ncols)
                )
                return pos.filter {
                    (row - it.row).absoluteValue <= 1 && range.contains(it.col)
                }
            }

            // keep track of gears
            val adjacentGears = mutableMapOf<Pos, MutableList<Int>>()
            // find parts (store pos)
            val parts = lines.flatMapIndexed { row, line ->
                "(\\d)+".toRegex().findAll(line)
                    .mapNotNull { it.groups.first() }
                    .filter { part ->
                        // track adjacent gears
                        findAdjacent(row, part.range, gearsCandidates).forEach {
                            adjacentGears.putIfAbsent(it, mutableListOf())
                            adjacentGears[it]!!.add(part.value.toInt())
                        }
                        findAdjacent(row, part.range, symbols).isNotEmpty()
                    }
                    .map { it.value.toInt() }
            }

            return Engine(parts, adjacentGears.values.filter { it.size > 1 }.map { it.reduce(Int::times) })
        }
    }


}

fun main() {
    // Part 1
    val engine = Engine.parse(readInput("day3"))
    println(engine.parts.sum())

    // Part 2
    println(engine.gears.sum())
}
