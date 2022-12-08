import kotlin.math.floor
import kotlin.math.roundToInt

fun main() {
    fun part1(input: List<String>): String {
        val slices = mutableListOf<List<String>>()
        val stacks = mutableListOf<MutableList<String>>()
        input.forEach { line ->
            if (line.contains("[")) {
                val numStacks = floor(line.length.div(4.0) + 1).roundToInt()
                val slice = (0 until numStacks)
                    .map { stackIndex -> (stackIndex * 4) + 1 }
                    .map { startIndex -> line.subSequence(startIndex, startIndex + 1).toString() }

                slices.add(slice)
            } else if (line.isEmpty()) {
                slices.forEach { slice ->
                    slice.forEachIndexed { index, crate ->
                        val stack = stacks.getOrNull(index)
                        if (crate.isBlank()) {
                            if (stack == null) {
                                stacks.add(mutableListOf())
                            }
                        } else {
                            if (stack == null) {
                                stacks.add(mutableListOf(crate))
                            } else {
                                stacks[index].add(crate)
                            }
                        }
                    }
                }
                stacks.forEach { it.reverse() }
            } else if (line.contains("move")) {
                val match = Regex("move ([0-9]+) from ([0-9]+) to ([0-9]+)").find(line)!!
                val (numberToMoveString, fromStackString, toStackString) = match.destructured
                val numberToMove = numberToMoveString.toInt()
                val fromStack = fromStackString.toInt()
                val toStack = toStackString.toInt()

                val stackToTakeFrom = stacks[fromStack - 1]
                val cratesToMove = stackToTakeFrom.subList(stackToTakeFrom.size - numberToMove, stackToTakeFrom.size).reversed()
                stacks[toStack - 1].addAll(cratesToMove)
                repeat(numberToMove) {
                    stackToTakeFrom.removeLast()
                }
            }
        }

        return stacks.joinToString(separator = "") { it.last() }
    }

    fun part2(input: List<String>): String {
        val slices = mutableListOf<List<String>>()
        val stacks = mutableListOf<MutableList<String>>()
        input.forEach { line ->
            if (line.contains("[")) {
                val numStacks = floor(line.length.div(4.0) + 1).roundToInt()
                val slice = (0 until numStacks)
                    .map { stackIndex -> (stackIndex * 4) + 1 }
                    .map { startIndex -> line.subSequence(startIndex, startIndex + 1).toString() }

                slices.add(slice)
            } else if (line.isEmpty()) {
                slices.forEach { slice ->
                    slice.forEachIndexed { index, crate ->
                        val stack = stacks.getOrNull(index)
                        if (crate.isBlank()) {
                            if (stack == null) {
                                stacks.add(mutableListOf())
                            }
                        } else {
                            if (stack == null) {
                                stacks.add(mutableListOf(crate))
                            } else {
                                stacks[index].add(crate)
                            }
                        }
                    }
                }
                stacks.forEach { it.reverse() }
            } else if (line.contains("move")) {
                val match = Regex("move ([0-9]+) from ([0-9]+) to ([0-9]+)").find(line)!!
                val (numberToMoveString, fromStackString, toStackString) = match.destructured
                val numberToMove = numberToMoveString.toInt()
                val fromStack = fromStackString.toInt()
                val toStack = toStackString.toInt()

                val stackToTakeFrom = stacks[fromStack - 1]
                val cratesToMove = stackToTakeFrom.subList(stackToTakeFrom.size - numberToMove, stackToTakeFrom.size)
                stacks[toStack - 1].addAll(cratesToMove)
                repeat(numberToMove) {
                    stackToTakeFrom.removeLast()
                }
            }
        }

        return stacks.joinToString(separator = "") { it.last() }
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day05_test")
    check(part1(testInput) == "CMZ")
    check(part2(testInput) == "MCD")

    val input = readInput("Day05")
    println(part1(input))
    println(part2(input))
}
