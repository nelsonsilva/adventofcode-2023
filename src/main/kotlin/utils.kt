import kotlinx.coroutines.*

fun readInput(name: String) = object {}.javaClass.getResource("$name.txt")!!.readText()

fun String.findAllLongs(): List<Long> {
    return "(\\d+)".toRegex().findAll(this).map { it.value.toLong() }.toList()
}

fun String.findAllNumbers(): List<Int> {
    return "(\\d+)".toRegex().findAll(this).map { it.value.toInt() }.toList()
}

fun <T, R> List<T>.pmap(
    mapper: suspend (T) -> R
): List<R> = runBlocking(Dispatchers.Default) { map { async { mapper(it) } }.awaitAll() }