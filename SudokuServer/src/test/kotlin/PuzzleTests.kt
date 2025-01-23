import com.example.puzzle.Puzzle
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class PuzzleTests {

    lateinit var testSeed:String
    lateinit var testPuzzle:Array<Array<String>>

    @BeforeEach
    fun generatePuzzle(){
        testSeed = "192543687657189324438627159824916573365278491719354268971462835243895716586731942"
        testPuzzle = arrayOf(
            arrayOf("1", "9", "2", "5", "4", "3", "6", "8", "7"),
            arrayOf("6", "5", "7", "1", "8", "9", "3", "2", "4"),
            arrayOf("4", "3", "8", "6", "2", "7", "1", "5", "9"),
            arrayOf("8", "2", "4", "9", "1", "6", "5", "7", "3"),
            arrayOf("3", "6", "5", "2", "7", "8", "4", "9", "1"),
            arrayOf("7", "1", "9", "3", "5", "4", "2", "6", "8"),
            arrayOf("9", "7", "1", "4", "6", "2", "8", "3", "5"),
            arrayOf("2", "4", "3", "8", "9", "5", "7", "1", "6"),
            arrayOf("5", "8", "6", "7", "3", "1", "9", "4", "2")
        )
    }

    @Test
    fun defaultSeedToPuzzle(){
        var puzzleInst = Puzzle()
        val defaultPuzzleGrid = puzzleInst.getGrid()
        val defaultPuzzleSolution = arrayOf<Array<String>>(
            arrayOf("5", "3", "4", "6", "7", "8", "9", "1", "2"),
            arrayOf("6", "7", "2", "1", "9", "5", "3", "4", "8"),
            arrayOf("1", "9", "8", "3", "4", "2", "5", "6", "7"),
            arrayOf("8", "5", "9", "7", "6", "1", "4", "2", "3"),
            arrayOf("4", "2", "6", "8", "5", "3", "7", "9", "1"),
            arrayOf("7", "1", "3", "9", "2", "4", "8", "5", "6"),
            arrayOf("9", "6", "1", "5", "3", "7", "2", "8", "4"),
            arrayOf("2", "8", "7", "4", "1", "9", "6", "3", "5"),
            arrayOf("3", "4", "5", "2", "8", "6", "1", "7", "9")
        )


        assertArrayEquals(defaultPuzzleGrid,defaultPuzzleSolution, "Puzzles do not match")

    }

    @Test
    fun seedToPuzzle() {
        val generatedPuzzle = Puzzle(testSeed).getGrid()

       assertArrayEquals(generatedPuzzle,testPuzzle, "Puzzles do not match")

    }


    @Test
    fun puzzleToSeed(){

        val puzzleInst = Puzzle()
        puzzleInst.setGrid(testPuzzle)
        val puzzleSeed = puzzleInst.puzzleToSeed()

        assertEquals(puzzleSeed,testSeed)
    }

    @Test
    fun swapRows() {

    }

    @Test
    fun swapColumns(){

    }


    @Test
    fun swapSymbols() {


    }

}