fun main() {
    fun part1(input: List<String>): Int {
        var elfNumber = 1
        val elfCalories = mutableMapOf<Int, Int>()
        var mostCalories = 0

        input.forEach { calories ->
            if (calories == "") {
                elfNumber++
            } else {
                val currentCalories = elfCalories.getOrDefault(elfNumber, 0)
                val newCalories = currentCalories + calories.toInt()
                elfCalories[elfNumber] = newCalories
                if (mostCalories < newCalories) {
                    mostCalories = newCalories
                }
            }
        }

        return mostCalories
    }

    fun part2(input: List<String>): Int {
        var elfNumber = 1
        val elfCalories = mutableMapOf<Int, Int>()

        input.forEach { calories ->
            if (calories == "") {
                elfNumber++
            } else {
                val currentCalories = elfCalories.getOrDefault(elfNumber, 0)
                val newCalories = currentCalories + calories.toInt()
                elfCalories[elfNumber] = newCalories
            }
        }

        var topThreeCalories = mutableListOf(0, 0, 0)
        elfCalories.values.forEach { calories ->
            topThreeCalories.add(calories)
            topThreeCalories.sortDescending()
            topThreeCalories = topThreeCalories.subList(0, 3)
        }

        return topThreeCalories.reduce { acc, i -> acc + i }
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day01_test")
    check(part1(testInput) == 24000)
    check(part2(testInput) == 45000)

    val input = readInput("Day01")
    println(part1(input))
    println(part2(input))
}
