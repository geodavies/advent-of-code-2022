fun String.splitInHalf(): Pair<String, String> {
    val numItems = length
    return substring(0 until numItems / 2) to substring(numItems / 2 until numItems)
}

fun Pair<String, String>.findDuplicate(): Char {
    val firstItems = first.toCharArray().toSet()
    return second.toCharArray().find { firstItems.contains(it) }!!
}

fun calculatePriority(item: Char): Int = if (item.isUpperCase()) {
    item.code - 38
} else {
    item.code - 96
}

fun findCommonItem(group: List<String>): Char {
    val allChars = mutableMapOf<Char, Int>()
    group.forEach { contents ->
        contents.toCharArray().toSet().forEach { item ->
            val currentCount = allChars.getOrDefault(item, 0)
            allChars[item] = currentCount + 1
        }
    }
    return allChars.entries.find { it.value == 3 }!!.key
}

fun main() {

    fun part1(input: List<String>) = input.map(String::splitInHalf)
        .map(Pair<String, String>::findDuplicate)
        .map { calculatePriority(it) }
        .reduce { acc, i -> acc + i }

    fun part2(input: List<String>): Int {
        val groups = mutableListOf<MutableList<String>>()
        input.forEachIndexed { idx, contents ->
            if (idx % 3 == 0) {
                groups.add(mutableListOf(contents))
            } else {
                groups[groups.size - 1].add(contents)
            }
        }

        return groups.map { findCommonItem(it) }
            .map { calculatePriority(it) }
            .sumAll()
    }


    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day03_test")
    check(part1(testInput) == 157)
    check(part2(testInput) == 70)

    val input = readInput("Day03")
    println(part1(input))
    println(part2(input))
}
