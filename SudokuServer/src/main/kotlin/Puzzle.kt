class Puzzle(private val seed: String = "534678912672195348198342567859761423426853791713924856961537284287419635345286179") {

    private val puzzle: Array<Array<String>> = loadSeed(seed)

    // Load seed string into a 2D array grid
    private fun loadSeed(seed: String): Array<Array<String>> {
        if (Math.sqrt(seed.length.toDouble()).toInt() != 9) {
            throw Exception("Invalid puzzle size, the input seed must be in a 9x9 grid (81 chars)")
        }
        return Array(9) { row ->
            Array(9) { col ->
                seed[row * 9 + col].toString()
            }
        }
    }
    //test
    // Return the current puzzle grid
    fun getGrid(): Array<Array<String>> = puzzle

    fun setGrid(newGrid: Array<Array<String>>) {
        for (row in newGrid.indices) {
            for (col in newGrid[row].indices) {
                puzzle[row][col] = newGrid[row][col]
            }
        }
    }

    fun printPuzzle() {
        for (rowIndex in puzzle.indices) {
            if (rowIndex % 3 == 0 && rowIndex > 0) {
                println("------+-------+------")
            }

            val row = puzzle[rowIndex]
            val rowString = buildString {
                for (colIndex in row.indices) {
                    append(row[colIndex])
                    if (colIndex % 3 == 2 && colIndex < row.lastIndex) {
                        append(" | ")
                    } else if (colIndex < row.lastIndex) {
                        append(" ")
                    }
                }
            }
            println(rowString)
        }
    }

    // Example manipulation methods
    fun swapRows(rowA: Int, rowB: Int): Array<Array<String>> {
        val newPuzzle = puzzle.map { it.clone() }.toTypedArray()
        val temp = newPuzzle[rowA]
        newPuzzle[rowA] = newPuzzle[rowB]
        newPuzzle[rowB] = temp
        return newPuzzle
    }

    fun swapColumns(colA: Int, colB: Int): Array<Array<String>> {
        val newPuzzle = puzzle.map { it.clone() }.toTypedArray()
        for (row in newPuzzle) {
            val temp = row[colA]
            row[colA] = row[colB]
            row[colB] = temp
        }
        return newPuzzle
    }

    fun swapSymbols(symbolA: String, symbolB: String): Array<Array<String>> {
        val newPuzzle = puzzle.map { it.clone() }.toTypedArray()
        for (row in newPuzzle) {
            for (col in row.indices) {
                when (row[col]) {
                    symbolA -> row[col] = symbolB
                    symbolB -> row[col] = symbolA
                }
            }
        }
        return newPuzzle
    }
}
