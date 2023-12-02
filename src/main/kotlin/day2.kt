enum class Color {
    red, green, blue
}
data class Game(val n: Int, val draws: List<Map<Color, Int>>) {
    companion object {
        // "Game 1: 3 blue, 4 red; 1 red, 2 green, 6 blue; 2 green",
        fun parse(str: String): Game {
            val group = Regex("Game (\\d+):").matchAt(str, 0)!!.groups[1]!!
            val draws = str.substring(group.range.last + 2).split(";").map {
                it.split(",").map {
                    val match = Regex("(\\d+) (\\w+)").matchEntire(it.trim())
                    Pair(Color.valueOf(match!!.groups[2]!!.value), match.groups[1]!!.value.toInt())
                }.toMap()
            }
            return Game(group.value.toInt(), draws)
        }
    }

    val min get() = draws.reduce { a, b -> Color.entries.associateWith { maxOf(a[it] ?: 0, b[it] ?: 0) } }

    val power get() = min.values.reduce(Int::times)
}

fun findPossible(bag: Map<Color, Int>, games: List<Game>): List<Game> {
    return games.filter { g -> g.min.entries.all { (bag[it.key] ?: 0) >= it.value } }
}

val games = readInput("day2").split("\n").map(Game::parse)

fun main() {
    // Part 1
    val bag = mapOf(Color.red to 12, Color.green to 13, Color.blue to 14)
    println(findPossible(bag, games).sumOf { it.n })
    // Part 2
    println(games.sumOf(Game::power))
}
