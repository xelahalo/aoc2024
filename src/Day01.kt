import kotlin.math.abs

fun main() {
    fun part1(input: List<String>): Int {
        val (left, right) = input.map { line ->
            val (a, b) = line.split("\\s+".toRegex()).map { it.toInt() }
            a to b
        }.unzip()

        return left.sorted().zip(right.sorted()).sumOf { (a, b) -> abs(b - a)  }
    }

    fun part2(input: List<String>): Int {
        val left = mutableListOf<Int>()
        val right = mutableMapOf<Int, Int>()

        input.forEach { line ->
            val (a,b) = line.split("\\s+".toRegex()).map { it.toInt() }
            left.add(a)
            right[b] = right.getOrDefault(b, 0) + 1
        }

        return left.sumOf { it * (right[it] ?: 0) }
    }

    val testInput = readInput("Day01_test")
    check(part1(testInput) == 11)
    check(part2(testInput) == 31)

    val input = readInput("Day01")
    part1(input).println()
    part2(input).println()
}
