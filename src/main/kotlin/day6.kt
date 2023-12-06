data class Race(val duration: Long, val record: Long)

fun parseRaces(str: String): List<Race> {
    val (times, distances) = str.split("\n").map(String::findAllLongs)
    return times.zip(distances).map { (d, r) -> Race(d, r)}
}

// returns (button hold time, distance) pairs
fun findOptions(duration: Long): List<Pair<Long, Long>> {
    return (1..<duration).map {
        val speed = it
        val time = duration - it
        it to speed * time
    }
}

fun findWins(race: Race): List<Long> = findOptions(race.duration).filter { it.second > race.record }.map { it.first }

fun main() {
    // Part 1
    val INPUT =
        "Time:        48     93     84     66\n" +
        "Distance:   261   1192   1019   1063"
    val races = parseRaces(INPUT)
    println(races.map { findWins(it).size }.reduce(Int::times) )

    // Part 2
    val race = parseRaces(
        "Time:        48938466\n" +
        "Distance:   261119210191063").first()
    println(findWins(race).size)
}