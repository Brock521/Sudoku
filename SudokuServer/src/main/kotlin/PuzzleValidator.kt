package com.example.puzzle

class PuzzleValidator(){

    fun validatePuzzle(puzzleSeed: String):Boolean{
        if(puzzleSeed.length != 81){
            return false
        }
        return validatePuzzle(Puzzle(puzzleSeed).getGrid())
    }

    fun validatePuzzle(puzzle: Array<Array<String>>) : Boolean{

        var symbolsSeenRow = mutableSetOf<String>()
        var symbolsSeenCol = mutableSetOf<String>()
        var size = 0

        /*Validate rows and columns. Values are added to the row and column sets as they are scanned over. If the item
        is already in the sets then there is a repetition and false is returned*/

        for(x in 0 until puzzle.size) {
            for (y in 0 until puzzle[x].size) {
                if (puzzle[x][y] in symbolsSeenRow) {
                    return false
                }

                if (puzzle[y][x] in symbolsSeenCol) {
                    return false
                }

                symbolsSeenRow.add(puzzle[x][y])
                symbolsSeenCol.add(puzzle[y][x])
            }

            symbolsSeenCol.clear()
            symbolsSeenRow.clear()
        }

        if(size != 81){
            return false
        }

        //Validate 3x3 sections one at a time. Pass the top left corner of each section to validateSection()
        for (rowStart in 0 until 9 step 3) {
            for (colStart in 0 until 9 step 3) {
                if (!validateSection(puzzle, rowStart, colStart)) {
                    return false
                }
            }
        }

        return true
    }

        private fun validateSection(puzzle: Array<Array<String>>, rowStart: Int, colStart: Int): Boolean {
            val symbolsSeen = mutableSetOf<String>()

            /*Visit each cell in the section and add the symbol to the set. if value can not be added it must already
            exist and thus the section is not unique */
            for (row in rowStart until rowStart + 3) {
                for (col in colStart until colStart + 3) {
                    if (puzzle[row][col] != "" && !symbolsSeen.add(puzzle[row][col])) {
                        return false
                    }
                }
            }
            return true
        }

}
