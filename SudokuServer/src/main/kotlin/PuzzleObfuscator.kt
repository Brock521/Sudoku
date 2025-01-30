package sudoku.server

    class PuzzleObfuscator(puzzleSeed: String) {

    data class ObfuscatedValue(var value: Char, val obfuscated: Boolean)

    var obfuscatedPuzzle: List<ObfuscatedValue>

    init {
        // Ensure the puzzle seed is valid (81 characters)
        require(puzzleSeed.length == 81) { "Puzzle seed must have exactly 81 characters." }

        // Initialize the puzzle with obfuscated values
        val minHints = 17
        val maxHints = 25
        val numHints = (Math.random() * (maxHints - minHints) + minHints).toInt()
        obfuscatedPuzzle = obfuscatePuzzle(puzzleSeed, numHints)
    }

    private fun obfuscatePuzzle(puzzleSeed: String, numHints: Int): List<ObfuscatedValue> {
        val visibleIndexes = mutableSetOf<Int>()

        // Randomly select unique indexes for visible cells
        while (visibleIndexes.size < numHints) {
            val index = (Math.random() * 81).toInt()
            visibleIndexes.add(index)
        }

        // Generate the obfuscated puzzle
        return List(81) { index ->
            val obfuscated = !visibleIndexes.contains(index) // Obfuscate if index is not in visibleIndexes
            ObfuscatedValue(puzzleSeed[index], obfuscated)
        }
    }

    fun printObfuscatedPuzzle() {
        for (row in 0 until 9) {
            if (row % 3 == 0 && row > 0) {
                println("------+-------+------")
            }

            val rowString = buildString {
                for (col in 0 until 9) {
                    val index = row * 9 + col
                    val value = if (obfuscatedPuzzle[index].obfuscated) {
                        "*" // Use '*' for obfuscated values
                    } else {
                        obfuscatedPuzzle[index].value.toString()
                    }
                    append(value)
                    if (col % 3 == 2 && col < 8) {
                        append(" | ")
                    } else if (col < 8) {
                        append(" ")
                    }
                }
            }
            println(rowString)
        }
    }
}
