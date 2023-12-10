
val CARDS = listOf('A', 'K', 'Q', 'J', 'T', '9', '8', '7', '6', '5', '4', '3', '2').reversed()
enum class HandType {
    HIGH_CARD,
    ONE_PAIR,
    TWO_PAIR,
    THREE_OF_A_KIND,
    FULL_HOUSE,
    FOUR_OF_A_KIND,
    FIVE_OF_A_KIND
}

fun rank(card: Char, jokers: Boolean = false): Int = if (jokers && card == 'J') -1 else CARDS.indexOf(card)

data class Hand(val cards: String, val jokers: Boolean = false): Comparable<Hand> {

    val type: HandType get() {
        var cards = this.cards
        if (jokers && cards.contains('J') && !cards.all { it == 'J' }) {
            // find best card
            val c = cards.filter { it != 'J' }.groupBy { it }.entries.sortedBy { it.value.size }.last().key
            cards = cards.replace('J', c)
        }
        val counts = cards.groupBy { it }.values.map { it.size }
        return when {
            counts.contains(5) -> HandType.FIVE_OF_A_KIND
            counts.contains(4) -> HandType.FOUR_OF_A_KIND
            counts.containsAll(listOf(3, 2)) -> HandType.FULL_HOUSE
            counts.contains(3) -> HandType.THREE_OF_A_KIND
            counts.count { it == 2 } == 2 -> HandType.TWO_PAIR
            counts.contains(2) -> HandType.ONE_PAIR
            else -> HandType.HIGH_CARD
        }
    }

    override fun compareTo(other: Hand): Int {
        var res = type.compareTo(other.type)
        if (res != 0) return res
        cards.forEachIndexed { i ,c ->
            res = rank(c, jokers).compareTo(rank(other.cards[i], jokers))
            if (res != 0) return res
        }
        return 0
    }

}

fun parseHands(str: String, jokers: Boolean = false): List<Pair<Hand, Int>> =
    str.split("\n")
        .map { it.split(" ").let { Hand(it[0], jokers) to it[1].toInt() } }
        .sortedBy { it.first }

fun winnings(hands: List<Pair<Hand, Int>>) = hands.foldIndexed(0L) { i, r, (_, b) -> r + (i + 1) * b }


fun main() {
    // Part 1
    println(winnings(parseHands(readInput("day7"))))
    // Part 2
    println(winnings(parseHands(readInput("day7"), jokers = true)))
}