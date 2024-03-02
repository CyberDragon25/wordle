
package edu.iastate.shubham8.wordle

import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*

class MainActivity : AppCompatActivity() {
    private lateinit var targetWord: String
    private var remainingGuesses = 3

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        targetWord = FourLetterWordList.getRandomFourLetterWord()
        val guessButton = findViewById<Button>(R.id.button)
        val guessInput = findViewById<EditText>(R.id.editTextTextPersonName)
        val resultTextView = findViewById<TextView>(R.id.textView)
        val scrollView = findViewById<ScrollView>(R.id.scrollView2)
        val linearLayout = scrollView.getChildAt(0) as LinearLayout

        guessButton.setOnClickListener {
            val guess = guessInput.text.toString()
            val result = checkGuess(guess)
            val guessTextView = TextView(this)
            guessTextView.text = guess
            guessTextView.textSize = 30f
            guessTextView.setPadding(8, 8, 8, 8)
            val borderDrawable = GradientDrawable()
            borderDrawable.setStroke(2, Color.BLACK)
            borderDrawable.cornerRadius = 8f
            guessTextView.background = borderDrawable
            linearLayout.addView(guessTextView)
            linearLayout.addView(TextView(this).apply { text = result })

            remainingGuesses--
            if (result == "OOOO") {
                resultTextView.text = "Congratulations! You guessed the word: $targetWord"
                guessButton.isEnabled = false
            } else if (remainingGuesses == 0) {
                resultTextView.text = "Out of guesses! The target word was: $targetWord"
                guessButton.isEnabled = false
            }
        }
    }

    private fun checkGuess(guess: String): String {
        if (guess.length != 4) {
            return "Invalid guess. Please enter exactly 4 characters."
        }
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