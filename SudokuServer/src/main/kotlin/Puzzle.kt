package com.example.puzzle;

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

    fun swapRows(blockA: Int, blockB: Int, newPuzzle: Array<Array<String>>): Array<Array<String>> {
        //We can not swap individual rows but will have to swap 9x3 rows...Ie) if the puzzle is made up of 9 chunks of  3x3 grids can we need to swap 3 3x3 grids for a whole row.

        if (blockA == blockB) {
            return newPuzzle
        }

        for (i in 0..2) {
            val temp = newPuzzle[blockA * 3 + i]
            newPuzzle[blockA * 3 + i] = newPuzzle[blockB * 3 + i]
            newPuzzle[blockB * 3 + i] = temp
        }
        return newPuzzle
    }

    fun swapColumns(blockA: Int, blockB: Int, newPuzzle: Array<Array<String>>): Array<Array<String>> {
        //We can not swap individual rows but will have to swap 3x9 rows...Ie) if the puzzle is made up of 9 chunks of  3x3 grids can we need to swap 3 3x3 grids for a whole row.

        if (blockA == blockB) {
            return newPuzzle
        }

        for (row in newPuzzle) {
            for (i in 0..2) {
                val temp = row[blockA * 3 + i]
                row[blockA * 3 + i] = row[blockB * 3 + i]
                row[blockB * 3 + i] = temp
            }
        }
        return newPuzzle
    }

    fun swapSymbols(symbolA: String, symbolB: String, newPuzzle: Array<Array<String>>): Array<Array<String>> {

        if(symbolA == symbolB){
            return newPuzzle
        }

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

    fun puzzleToSeed(): String {
        val builder = StringBuilder()
        for (row in puzzle) {
            for (cell in row) {
                builder.append(cell)
            }
        }
        return builder.toString()
    }

    fun randomizePuzzle() {
        val newPuzzle = puzzle.map { it.clone() }.toTypedArray()
        val numRandomChanges = (1..25).random()

        val actions = listOf<(Array<Array<String>>) -> Array<Array<String>>>(
            { newPuzzle -> swapRows((0..2).random(), (0..2).random(), newPuzzle) },
            { newPuzzle -> swapColumns((0..2).random(), (0..2).random(), newPuzzle) },
            { newPuzzle -> swapSymbols((1..9).random().toString(), (1..9).random().toString(), newPuzzle) }
        )

        for (iteration in 1..numRandomChanges) {
            val action = actions.random()  // Select a random action
            action(newPuzzle)  // Apply the selected action to the current puzzle
        }

        setGrid(newPuzzle);

    }

}

fun main(){
    val puzzleInst = Puzzle()
        puzzleInst.printPuzzle()
    println();
    for(num in 0..5) {
        puzzleInst.randomizePuzzle()
        puzzleInst.printPuzzle()
        println();
    }

}
