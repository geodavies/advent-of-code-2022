import Outcome.DRAW
import Outcome.LOSE
import Outcome.WIN
import Shape.PAPER
import Shape.ROCK
import Shape.SCISSORS

enum class Shape(private val mappings: List<String>) {
    ROCK(listOf("A", "X")), PAPER(listOf("B", "Y")), SCISSORS(listOf("C", "Z"));

    companion object {
        fun of(input: String): Shape {
            return values().find { it.mappings.contains(input) }!!
        }
    }
}

enum class Outcome(private val input: String, val score: Int) {
    LOSE("X", 0), DRAW("Y", 3), WIN("Z", 6);

    companion object {
        fun of(input: String): Outcome {
            return values().find { it.input == input }!!
        }
    }
}

val shapeToScore = mapOf(
    ROCK to 1,
    PAPER to 2,
    SCISSORS to 3,
)

val combinations = mapOf(
    (ROCK to ROCK) to 3,
    (ROCK to PAPER) to 6,
    (ROCK to SCISSORS) to 0,
    (PAPER to ROCK) to 0,
    (PAPER to PAPER) to 3,
    (PAPER to SCISSORS) to 6,
    (SCISSORS to ROCK) to 6,
    (SCISSORS to PAPER) to 0,
    (SCISSORS to SCISSORS) to 3,
)

val outcomeCombinations = mapOf(
    (ROCK to LOSE) to SCISSORS,
    (ROCK to DRAW) to ROCK,
    (ROCK to WIN) to PAPER,
    (PAPER to LOSE) to ROCK,
    (PAPER to DRAW) to PAPER,
    (PAPER to WIN) to SCISSORS,
    (SCISSORS to LOSE) to PAPER,
    (SCISSORS to DRAW) to SCISSORS,
    (SCISSORS to WIN) to ROCK,
)

fun main() {

    fun part1(input: List<String>) = input.map {
        val split = it.split(" ")
        Shape.of(split[0]) to Shape.of(split[1])
    }.map {
        shapeToScore[it.second]!! + combinations[it.first to it.second]!!
    }.reduce { acc, i -> acc + i }

    fun part2(input: List<String>) = input.map {
        val split = it.split(" ")
        Shape.of(split[0]) to Outcome.of(split[1])
    }.map {
        val myShape = outcomeCombinations[it.first to it.second]!!
        shapeToScore[myShape]!! + it.second.score
    }.reduce { acc, i -> acc + i }


    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day02_test")
    check(part1(testInput) == 15)
    check(part2(testInput) == 12)

    val input = readInput("Day02")
    println(part1(input))
    println(part2(input))
}
