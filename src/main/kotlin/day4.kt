import kotlin.math.pow

data class Card(val id: Int, val winning: List<Int>, val have: List<Int>) {
    companion object {
        // Card 1: 41 48 83 86 17 | 83 86  6 31 17  9 48 53,
        fun parse(str: String): Card {
            val (card, numberStr) = str.split(":")
            val id = card.split(" ").last().toInt()
            val (winning, numbers) = numberStr.split("|").map {
                "(\\d+)".toRegex().findAll(it).map { it.value.toInt() }.toList()
            }
            return Card(id, winning, numbers)
        }
    }

    val matching get() = have.intersect(winning).size

    val points get() = 2.toDouble().pow(matching - 1).toInt()

}

fun totalCards(cards: List<Card>): Int {
    val won = cards.associate { it.id to 1 }.toMutableMap()
    cards.forEach {
        for (i in it.id + 1..(it.id + it.matching)) {
            won[i] = won[i]!! + won[it.id]!!
        }
    }
    return won.values.sum()
}

val cards = readInput("day4").split("\n").map(Card::parse)

fun main() {
    // Part 1
    println(cards.sumOf { it.points })
    // Part 2
    println(totalCards(cards))
}
