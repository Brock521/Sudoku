import com.example.puzzle.PuzzleManager
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class PuzzleManagerTests {

    lateinit var testSeed:String
    lateinit var testPuzzle:Array<Array<String>>
    lateinit var  defaultSeed:String
    @BeforeEach
    fun generatePuzzle(){
        testSeed = "192543687657189324438627159824916573365278491719354268971462835243895716586731942"
        defaultSeed = "534678912672195348198342567859761423426853791713924856961537284287419635345286179"
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
        var puzzleInst = PuzzleManager()
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
        val generatedPuzzle = PuzzleManager(testSeed).getGrid()

       assertArrayEquals(generatedPuzzle,testPuzzle, "Puzzles do not match")

    }


    @Test
    fun puzzleToSeed(){

        val puzzleInst = PuzzleManager()
        puzzleInst.setGrid(testPuzzle)
        val puzzleSeed = puzzleInst.puzzleToSeed()

        assertEquals(puzzleSeed,testSeed)
    }

    @Test
    fun swapRows() {


        val puzzleManager = PuzzleManager(testSeed)
        testPuzzle = puzzleManager.getGrid()

        val originalPuzzle = Array(9) { row -> testPuzzle[row].clone() }

        puzzleManager.swapRows(0,2)

        val swappedGrid = puzzleManager.getGrid()

        //Bottom Row
        assertArrayEquals(swappedGrid[0],originalPuzzle[6])
        assertArrayEquals(swappedGrid[1],originalPuzzle[7])
        assertArrayEquals(swappedGrid[2],originalPuzzle[8])

        //Top row
        assertArrayEquals(swappedGrid[6],originalPuzzle[0])
        assertArrayEquals(swappedGrid[7],originalPuzzle[1])
        assertArrayEquals(swappedGrid[8],originalPuzzle[2])


    }

    @Test
    fun swapColumns() {

        val puzzleManager = PuzzleManager(testSeed)
        val testPuzzle = puzzleManager.getGrid()

        val originalPuzzle = Array(9) { row -> testPuzzle[row].clone() }

        puzzleManager.swapColumns(0, 1)

        val swappedGrid = puzzleManager.getGrid()

        // Ensure the columns have been swapped
        for (row in 0 until 9) {
            assertEquals(swappedGrid[row][3], originalPuzzle[row][0])
            assertEquals(swappedGrid[row][4], originalPuzzle[row][1])
            assertEquals(swappedGrid[row][5], originalPuzzle[row][2])

            assertEquals(swappedGrid[row][0], originalPuzzle[row][3])
            assertEquals(swappedGrid[row][1], originalPuzzle[row][4])
            assertEquals(swappedGrid[row][2], originalPuzzle[row][5])
        }
    }


    @Test
    fun swapSymbols() {
        val puzzleManager = PuzzleManager(testSeed)
        val testPuzzle = puzzleManager.getGrid()

        // Create a deep copy of the puzzle to preserve the original state
        val originalPuzzle = Array(9) { row -> testPuzzle[row].clone() }

        // Swap symbols "1" and "9"
        puzzleManager.swapSymbols("1", "9")

        val swappedGrid = puzzleManager.getGrid()

        // Ensure that the symbols "1" and "9" are swapped
        for (row in 0 until 9) {
            for (col in 0 until 9) {
                when (originalPuzzle[row][col]) {
                    "1" -> assertEquals(swappedGrid[row][col], "9")  // Symbol "1" should become "9"
                    "9" -> assertEquals(swappedGrid[row][col], "1")  // Symbol "9" should become "1"
                    else -> assertEquals(swappedGrid[row][col], originalPuzzle[row][col])  // Other symbols remain unchanged
                }
            }
        }
    }

    @Test
    fun randomize() {
        val puzzleManager = PuzzleManager(testSeed)
        val puzzle = puzzleManager.getGrid()

        assertArrayEquals(puzzle, testPuzzle, "The built puzzle does not match the test puzzle")

        // Randomize the puzzle
        puzzleManager.randomizePuzzle()

        assertFalse(
            puzzleManager.getGrid().contentDeepEquals(testPuzzle),
            "The randomized puzzle matches the test puzzle"
        )
    }

}