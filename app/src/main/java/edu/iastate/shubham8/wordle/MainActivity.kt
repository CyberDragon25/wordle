// MainActivity.kt
package edu.iastate.shubham8.wordle

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    private lateinit var targetWord: String
    private var remainingGuesses = 3

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        targetWord = FourLetterWordList.getRandomFourLetterWord()
        val guessButton = findViewById<Button>(R.id.button)
        val guessInput = findViewById<EditText>(R.id.editTextTextPersonName)

    }

    private fun checkGuess(guess: String): String {
        var result = ""
        for (i in 0 until 4) {
            if (guess[i] == targetWord[i]) {
                result += "O"
            } else if (targetWord.contains(guess[i])) {
                result += "+"
            } else {
                result += "X"
            }
        }
        return result
    }
}
