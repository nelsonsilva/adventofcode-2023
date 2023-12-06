import kotlinx.coroutines.*

fun readInput(name: String) = object {}.javaClass.getResource("$name.txt")!!.readText()

fun String.findAllNumbers(): List<Long> {
    return "(\\d+)".toRegex().findAll(this).map { it.value.toLong() }.toList()
}

fun <T, R> List<T>.pmap(
    mapper: suspend (T) -> R
): List<R> = runBlocking(Dispatchers.Default) { map { async { mapper(it) } }.awaitAll() }