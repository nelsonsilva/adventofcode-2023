import kotlin.math.min

data class Almanac(
    var seeds: List<Long>,
    val maps: List<Map<LongRange, LongRange>>) {
    companion object {
        // Card 1: 41 48 83 86 17 | 83 86  6 31 17  9 48 53,
        fun parse(str: String): Almanac {
            val lines = str.split("\n")
            val seeds = lines.first().findAllLongs()
            val maps = mutableListOf<MutableMap<LongRange, LongRange>>()
            lines.subList(1, lines.size).filter { it.isNotBlank() }.forEach {line ->
                val map = "(.+) map:".toRegex().find(line)
                if (map != null) {
                    maps.add(mutableMapOf())
                } else {
                    val (dst, src, length) = line.findAllLongs()
                    maps.last()[src..<src + length] = dst..<dst + length
                }
            }
            return Almanac(seeds, maps)
        }
    }

    fun lookup(what: Long, map: Map<LongRange, LongRange>): Long {
        val src = map.keys.firstOrNull { it.contains(what) } ?: return what
        return (what - src.first) + map[src]!!.first
    }

    fun lookup(seed: Long) = maps.fold(seed) { what, map -> lookup(what, map) }

    fun lookup() = seeds.map { lookup(it) }
}

val almanac = Almanac.parse(readInput("day5"))

fun part2(almanac: Almanac): Long = almanac.seeds.chunked(2).pmap { (start, length) ->
    (start..<(start + length)).fold(Long.MAX_VALUE) {m, e -> min(m, almanac.lookup(e))}
}.reduce(::min)

fun main() {
    // Part 1
    println(almanac.lookup().min())
    // Part 2
    println(part2(almanac))
}
