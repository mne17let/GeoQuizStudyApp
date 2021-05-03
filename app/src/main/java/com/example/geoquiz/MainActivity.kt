package com.example.geoquiz

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.example.geoquiz.ModelClasses.Question

class MainActivity : AppCompatActivity() {

    var mainActivity = this

    private lateinit var trueButtonVar: Button
    private lateinit var falseButtonVar: Button
    private lateinit var nextButtonVar: Button
    private lateinit var textViewQuestionVar: TextView

    private val questionBank = mutableListOf<Question>()
    private var currentIndex = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        init()
        createQuestions()
        fillTextView()
    }

    fun init(){
        trueButtonVar = findViewById(R.id.id_true_button)
        falseButtonVar = findViewById(R.id.id_false_button)
        nextButtonVar = findViewById(R.id.id_next_button)
        textViewQuestionVar = findViewById(R.id.id_textview_question)

        val instanceTrueListener = trueButtonListener()
        val instanceFalseListener = falseButtonListener()
        val instanceNextListener = nextButtonListener()

        trueButtonVar.setOnClickListener(instanceTrueListener)
        falseButtonVar.setOnClickListener(instanceFalseListener)
        nextButtonVar.setOnClickListener(instanceNextListener)
    }

    fun createQuestions(){
        val question1 = Question(R.string.string_question1_text, true)
        val question2 = Question(R.string.string_question2_text, true)
        val question3 = Question(R.string.string_question3_text, true)
        val question4 = Question(R.string.string_question4_text, false)
        val question5 = Question(R.string.string_question5_text, false)
        val question6 = Question(R.string.string_question6_text, false)
        val question7 = Question(R.string.string_question7_text, false)
        val question8 = Question(R.string.string_question8_text, false)
        val question9 = Question(R.string.string_question9_text, true)

        questionBank.add(question1)
        questionBank.add(question2)
        questionBank.add(question3)
        questionBank.add(question4)
        questionBank.add(question5)
        questionBank.add(question6)
        questionBank.add(question7)
        questionBank.add(question8)
        questionBank.add(question9)
    }

    fun fillTextView(){
        val currentQuestionTextId = questionBank[currentIndex].textId
        textViewQuestionVar.setText(currentQuestionTextId)
    }

    fun checkAnswer(userAnswer: Boolean){
        val currentRightAnswer = questionBank[currentIndex].rightAnswer
        val resultMessageText = if (currentRightAnswer == userAnswer){
            R.string.string_correct_answer
        } else {
            R.string.string_false_answer
        }

        Toast.makeText(this, resultMessageText, Toast.LENGTH_SHORT).show()
    }

    inner class trueButtonListener: View.OnClickListener{
        override fun onClick(v: View?) {
            checkAnswer(true)
        }
    }

    inner class falseButtonListener: View.OnClickListener{
        override fun onClick(v: View?) {
            checkAnswer(false)
        }
    }

    inner class nextButtonListener: View.OnClickListener{
        override fun onClick(v: View?) {
            currentIndex = (currentIndex + 1) % questionBank.size
            fillTextView()
        }

    }
}