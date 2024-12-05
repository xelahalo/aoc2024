fun main() {
    fun parseRules(input: List<String>): Map<Int, Set<Int>> {
        val rules = mutableMapOf<Int, Set<Int>>()
        input.forEach {
            val rulePart = it.split("|").map(String::toInt)
            rules[rulePart[0]] = rules.getOrDefault(rulePart[0], setOf()).plus(rulePart[1])
        }

        return rules
    }

    fun isCorrectSeq(seq: List<Int>, rules: Map<Int, Set<Int>>): Boolean {
        val nums = mutableSetOf<Int>()
        seq.forEach { num ->
            val rule = rules.getOrDefault(num, setOf())
            if (nums.intersect(rule).isNotEmpty()) return false
            nums.add(num)
        }

        return true
    }

    fun part1(input: List<String>): Int {
        val delim = input.indexOfFirst { it.isEmpty() }
        val rules = parseRules(input.subList(0, delim))

        return input.subList(delim + 1, input.size)
            .map { it.split(",").map(String::toInt) }
            .filter { isCorrectSeq(it, rules) }
            .sumOf { it[it.size / 2] }
    }

    fun part2(input: List<String>): Int {
        val delim = input.indexOfFirst { it.isEmpty() }
        val rules = parseRules(input.subList(0, delim))

        return input.subList(delim + 1, input.size)
            .map { it.split(",").map(String::toInt) }
            .filter { !isCorrectSeq(it, rules) }
            .sumOf {
                val sorted = it.sortedWith { o1, o2 ->
                    when {
                        rules.getOrDefault(o1, setOf()).contains(o2) -> -1
                        rules.getOrDefault(o2, setOf()).contains(o1) -> 1
                        else -> 0
                    }
                }

                sorted[sorted.size / 2]
            }
    }

    val testInput = readInput("Day05_test")
    check(part1(testInput) == 143)
    check(part2(testInput) == 123)

    val input = readInput("Day05")
    part1(input).println()
    part2(input).println()
}
