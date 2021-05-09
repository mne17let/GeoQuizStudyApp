package com.example.geoquiz.ViewModelClasses

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.geoquiz.ModelClasses.Question
import com.example.geoquiz.R

private const val tagFromViewModel = "ViewModel_Vladimir"

class QuizViewModel: ViewModel() {

    var currentIndex = 0
    private val questionBank = mutableListOf<Question>()

    val currentRightAnswer: Boolean
        get() = questionBank[currentIndex].rightAnswer

    val currentQuestionTextId: Int
        get() = questionBank[currentIndex].textId


    init{
        Log.d(tagFromViewModel, "ViewModel создана")
    }

    override fun onCleared() {
        super.onCleared()
        Log.d(tagFromViewModel, "ViewModel удалена")
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

    fun moveToNextQuestion(){
        currentIndex = (currentIndex + 1) % questionBank.size
    }

    fun moveToPreviewQuestion(){
        if ((currentIndex - 1) < 0){
            currentIndex = 8
        } else {
            currentIndex = (currentIndex - 1) % questionBank.size
        }
    }

}