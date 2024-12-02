import kotlin.math.abs

fun main() {
    fun isSafe(report: List<Int>): Boolean {
        var direction: Boolean? = null

        for (i in 0 until report.lastIndex) {
            val current = report[i]
            val next = report[i + 1]

            val currentDirection = next > current

            if (next == current || abs(next - current) > 3 || (direction != null && currentDirection != direction)) {
                return false
            }

            direction = currentDirection
        }


        return true
    }

    fun part1(input: List<String>): Int =
        input
            .map { line ->
                line.split(" ").map { it.toInt() }
            }
            .count { isSafe(it) }

    fun part2(input: List<String>): Int =
        input
            .map { line ->
                line.split(" ").map { it.toInt() }
            }
            .count { report ->
                report.indices.any { index ->
                    val filteredByIndex = report.filterIndexed { i, _ -> i != index }
                    isSafe(filteredByIndex)
                }
            }


    val testInput = readInput("Day02_test")
    check(part1(testInput) == 2)
    check(part2(testInput) == 4)

    val input = readInput("Day02")
    part1(input).println()
    part2(input).println()
}
