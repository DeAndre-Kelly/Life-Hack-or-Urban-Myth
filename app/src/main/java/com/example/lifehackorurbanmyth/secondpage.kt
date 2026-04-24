package com.example.lifehackorurbanmyth

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import kotlin.jvm.java

class secondpage : AppCompatActivity() {


    //Declaration of variables

    private val questions = arrayOf(
        "Putting your phone in rice will fix water damage.",
        "Emotional or exaggerated language can be a sign of misinformation.",
        "Regular exercise can improve mental health.",
        "Public Wi-Fi is always safe if it doesn't require a password.",
        "Incognito mode makes you completely anonymous online."
    )

    private val answers = booleanArrayOf(false, true, true, false, false)

    private var currentIndex = 0

    private var score = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_secondpage)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        //Initialise UI elements
        val tvQuestion = findViewById<TextView>( R.id.tvQuestion)
        val tvFeedback = findViewById<TextView>( R.id.tvFeedback)
        val btnTrue = findViewById<Button>(R.id.btnTrue)
        val btnFalse = findViewById<Button>(R.id.btnFalse)
        val btnNext = findViewById<Button>( R.id.btnNext)


        //Initialize first question
        tvQuestion.text = questions[currentIndex]
        val checkAnswer = {userSelection: Boolean ->
            tvFeedback.visibility = View.VISIBLE


            if (userSelection == answers[currentIndex]) {
                // use the built-in Color constants
                tvFeedback.text = "Correct!"
                tvFeedback.setTextColor(Color.GREEN)
                score++
                } else {
                    tvFeedback.text = "Incorrect!"
                    tvFeedback.setTextColor(Color.RED)

            }

            //Disable answer buttons so they can't click twice
            btnTrue.isEnabled = false
            btnFalse.isEnabled = false
            btnNext.isEnabled = true
        }

        btnTrue.setOnClickListener { checkAnswer(true) }
        btnFalse.setOnClickListener { checkAnswer(false) }

        btnNext.setOnClickListener {
            currentIndex++
            if (currentIndex < questions.size) {
                //load next question
                tvQuestion.text = questions[currentIndex]
                tvFeedback.visibility = View.INVISIBLE
                btnTrue.isEnabled = true
                btnFalse.isEnabled = true
                btnNext.isEnabled = false
            } else {
                //Finished: Go to Score Screen
                val intent = Intent( this, Score::class.java)
                intent.putExtra("TOTAL_SCORE",  score)
                startActivity(intent)
                finish()

            }
        }
    }

}