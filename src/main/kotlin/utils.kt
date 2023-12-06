fun readInput(name: String) = object {}.javaClass.getResource("$name.txt")!!.readText()

fun String.findAllNumbers(): List<Long> {
    return "(\\d+)".toRegex().findAll(this).map { it.value.toLong() }.toList()
}