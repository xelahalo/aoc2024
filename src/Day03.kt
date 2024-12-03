fun main() {
    val multiplications = Regex("mul\\((\\d{1,3}),(\\d{1,3})\\)")
    val withInstructions = Regex("mul\\((\\d{1,3}),(\\d{1,3})\\)|(do\\(\\))|(don't\\(\\))")

    fun part1(input: List<String>): Int =
        multiplications.findAll(input.joinToString())
            .sumOf { match ->
                match.groupValues
                    .drop(1)
                    .map { it.toInt() }
                    .zipWithNext { a, b -> a * b }
                    .sum()
            }

    fun part2(input: List<String>): Int {
        var take = true

        return withInstructions.findAll(input.joinToString())
            .sumOf { match ->
                match.groupValues
                    .asSequence()
                    .drop(1)
                    .mapNotNull {
                        take = when (it) {
                            "do()" -> true
                            "don't()" -> false
                            else -> take
                        }

                        it.toIntOrNull()
                    }
                    .filter { take }
                    .zipWithNext { a, b -> a * b }
                    .sum()
            }
    }


    val testInput = readInput("Day03_test")
    check(part1(testInput) == 161)
    check(part2(testInput) == 48)

    val input = readInput("Day03")
    part1(input).println()
    part2(input).println()
}
