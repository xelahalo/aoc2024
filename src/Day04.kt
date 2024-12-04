fun main() {
    val xmas: CharSequence = "XMAS"

    fun findXMAS(input: List<String>, i: Int, j: Int, dx: Int, dy: Int, iter: Int = 0): Int {
        if (iter == xmas.length) return 1

        val char = input.getOrNull(i)?.getOrNull(j)
        if (char != xmas[iter]) return 0

        return findXMAS(input, i + dx, j + dy, dx, dy, iter + 1)
    }

    fun isXmas(input: List<String>, i: Int, j: Int): Boolean {
        fun mors(char: Char?) = char == 'M' || char == 'S'

        if (input[i][j] != 'A') return false

        val tl = input.getOrNull(i - 1)?.getOrNull(j - 1)
        val tr = input.getOrNull(i - 1)?.getOrNull(j + 1)
        val bl = input.getOrNull(i + 1)?.getOrNull(j - 1)
        val br = input.getOrNull(i + 1)?.getOrNull(j + 1)

        if (!mors(tl) || !mors(tr) || !mors(bl) || !mors(br)) return false

        return tl != br && tr != bl
    }


    fun part1(input: List<String>): Int {
        val directions = listOf(
            -1 to -1, -1 to 0, -1 to 1,
             0 to -1,           0 to 1,
             1 to -1,  1 to 0,  1 to 1
        )

        return input.indices.sumOf { i ->
            input[i].indices.sumOf { j ->
                directions.sumOf { (dx, dy) -> findXMAS(input, i, j, dx, dy) }
            }
        }
    }

    fun part2(input: List<String>): Int =
        input.indices.sumOf { i -> input[i].indices.count { j -> isXmas(input, i, j) } }

    val testInput = readInput("Day04_test")
    check(part1(testInput) == 18)
    check(part2(testInput) == 9)

    val input = readInput("Day04")
    part1(input).println()
    part2(input).println()
}
