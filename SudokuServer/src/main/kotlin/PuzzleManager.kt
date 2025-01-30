package sudoku.server;

class PuzzleManager(private val seed: String = "534678912672195348198342567859761423426853791713924856961537284287419635345286179") {

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

    fun swapRows(blockA: Int, blockB: Int) {

        if (blockA == blockB) return  // No change if the blocks numbers are the same

        if((blockA in 0..2) == false || (blockB in 0..2 == false)){
            throw IllegalArgumentException("Row swap values must be between [0-2]")
        }

        // Swap the rows directly in the puzzle
        for (i in 0..2) {
            val temp = puzzle[blockA * 3 + i]
            puzzle[blockA * 3 + i] = puzzle[blockB * 3 + i]
            puzzle[blockB * 3 + i] = temp
        }
    }

    fun swapColumns(blockA: Int, blockB: Int) {

        if (blockA == blockB) return  // No change if the blocks numbers are the same

        if((blockA in 0..2) == false || (blockB in 0..2 == false)){
            throw IllegalArgumentException("Column swap values must be between [0-2]")
        }

        // Swap the columns directly in the puzzle
        for (row in puzzle) {
            for (i in 0..2) {
                val temp = row[blockA * 3 + i]
                row[blockA * 3 + i] = row[blockB * 3 + i]
                row[blockB * 3 + i] = temp
            }
        }
    }

    fun swapSymbols(symbolA: String, symbolB: String) {
        if (symbolA == symbolB) return  // No change if symbols are the same

        // Swap the symbols directly in the puzzle
        for (row in puzzle) {
            for (col in row.indices) {
                when (row[col]) {
                    symbolA -> row[col] = symbolB
                    symbolB -> row[col] = symbolA
                }
            }
        }
    }

    fun getSeed(): String {
        val builder = StringBuilder()
        for (row in puzzle) {
            for (cell in row) {
                builder.append(cell)
            }
        }
        return builder.toString()
    }

    fun randomizePuzzle() {
        val numRandomChanges = (1..25).random()

        val actions = listOf(
            { swapRows((0..2).random(), (0..2).random()) },
            { swapColumns((0..2).random(), (0..2).random()) },
            { swapSymbols((1..9).random().toString(), (1..9).random().toString()) }
        )

        for (iteration in 1..numRandomChanges) {
            val action = actions.random()  // Select a random action
            action.invoke()  // Perform the random action
        }
    }


}
