fun String.toPairOfAssignments(): Pair<Pair<Int, Int>, Pair<Int, Int>> {
    val pairs = split(",")
    val assignment1 = pairs[0].split("-")
    val assignment2 = pairs[1].split("-")
    return (assignment1[0].toInt() to assignment1[1].toInt()) to (assignment2[0].toInt() to assignment2[1].toInt())
}

fun isContained(firstAssignment: Pair<Int, Int>, secondAssignment: Pair<Int, Int>) =
    if (firstAssignment.first >= secondAssignment.first && firstAssignment.second <= secondAssignment.second) {
        true
    } else secondAssignment.first >= firstAssignment.first && secondAssignment.second <= firstAssignment.second

fun doTheyOverlap(firstAssignment: Pair<Int, Int>, secondAssignment: Pair<Int, Int>) =
    if (firstAssignment.first >= secondAssignment.first && firstAssignment.first <= secondAssignment.second) {
        true
    } else firstAssignment.second >= secondAssignment.first && firstAssignment.second <= secondAssignment.second

fun main() {
    fun part1(input: List<String>) = input
        .map(String::toPairOfAssignments)
        .map {
            if (isContained(it.first, it.second)) {
                1
            } else {
                0
            }
        }.sumAll()


    fun part2(input: List<String>) = input
        .map(String::toPairOfAssignments)
        .map {
            if (isContained(it.first, it.second) || doTheyOverlap(it.first, it.second)) {
                1
            } else {
                0
            }
        }.sumAll()

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day04_test")
    check(part1(testInput) == 2)
    check(part2(testInput) == 4)

    val input = readInput("Day04")
    println(part1(input))
    println(part2(input))
}
