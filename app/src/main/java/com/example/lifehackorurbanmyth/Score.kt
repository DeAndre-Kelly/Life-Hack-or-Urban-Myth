/**
 * Project: Life Hack or Urban Myth
 * Student Name: DeAndre Kelly
 * Student Number:ST10517479
 */


package com.example.lifehackorurbanmyth

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import kotlin.system.exitProcess


class Score : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_score)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // 1. Get the score passed from secondpageActivity
        val score = intent.getIntExtra( "TOTAL_SCORE",  0)

        // 2. Initilialize UI elements
        val tvScore = findViewById<TextView>( R.id.tvScore)
        val tvMessage = findViewById<TextView>( R.id.tvMessage)
        val btnReview = findViewById<Button>(R.id.btnReview)
        val btnExit = findViewById<Button>(R.id.btnExit)

        // 3. Display the score
        tvScore.text = "Total Correct: $score / 5"

        // 4. Personalised feedback logic (Requirement: >= 3 is "Great Job!")
        if (score >= 3) {
            tvMessage.text = "Great Job!"
        } else {
            tvMessage.text = "Keep practising!"
        }

        // 5. Review Button Logic - Shows on AlertDialog with questions and answers
        btnReview.setOnClickListener {
            val reviewData = arrayOf(
                "1. It might help slighlty, but it's unreliable and not a real fix: False",
                "2. Emotional or exaggerated language can be a sign of misinformation: True",
                "3. It reduces stress, anxiety, and depression symptoms: True",
                "4. It's often less secure: False",
                "5. It only hides local browsing history: False"
            )

            val builder = AlertDialog.Builder( this)
            builder.setTitle("Quiz Review")
            builder.setItems(reviewData, null) // Displays the array
            builder.setPositiveButton("Close")  { dialog, _ ->
                dialog.dismiss()
            }

            val dialog = builder.create()
            dialog.show()

        }

        //6. Exit Button Logic - Terminate the app
        btnExit.setOnClickListener {
            finishAffinity() // Closes all activities in the task
            exitProcess( status = 0) //Exits the app
        }
    }
}