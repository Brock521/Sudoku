package com.example.puzzle

import java.util.zip.DataFormatException

class PuzzleObfuscator(puzzleSeed: String) {

    data class ObfuscatedValue(val value: Char, val obfuscated: Boolean)

    var obfuscatedPuzzle: List<ObfuscatedValue?>

    init {

        obfuscatedPuzzle = List<ObfuscatedValue?>(0){it -> null}

        val minHints = 17
        val maxHints = 25
        val numHints = (Math.random() * (maxHints - minHints)  + minHints).toInt()
        obfuscatePuzzle(puzzleSeed,numHints)
    }


    fun obfuscatePuzzle(puzzleSeed: String, numHints:Int) {
        var visibleIndexes = Array<Int>(numHints) { it -> -1 }

        var uniqueIndexNum = 0;
        while (uniqueIndexNum < visibleIndexes.size) {

            val index = (Math.random() * 81).toInt()
            if (visibleIndexes.contains(index) == false) {
                visibleIndexes[uniqueIndexNum] = index
                uniqueIndexNum++
            }

        }

        var tempObfuscatedPuzzle = MutableList<ObfuscatedValue?>(0){it -> null}

        for(index in 0 until 81){

            var obfuscated = false

            if(index in visibleIndexes){
                obfuscated = true
            } else {
                obfuscated = false
            }

            tempObfuscatedPuzzle.add(ObfuscatedValue(puzzleSeed.get(index),obfuscated))

        }

        if(tempObfuscatedPuzzle.size == 81){
            obfuscatedPuzzle = tempObfuscatedPuzzle
        } else {
            throw IllegalArgumentException("Size of the puzzle does not match ")
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
                    val value = if (obfuscatedPuzzle[index]?.obfuscated == true) {
                        obfuscatedPuzzle[index]?.value.toString()
                    } else {
                        " "
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
