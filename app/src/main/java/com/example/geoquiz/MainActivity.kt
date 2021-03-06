package com.example.geoquiz

import android.app.Activity
import android.app.ActivityOptions
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.geoquiz.ModelClasses.Question
import com.example.geoquiz.ViewModelClasses.QuizViewModel

private const val tagFromActivity = "MainActivity_Vladimir"
private const val tagForCurrentIndexInBundle = "MainActivity_CurrentQuestionIndex_ForBundle"
private const val stringExtraForIntentIfAnswerIsTrue = "package_name.Answer_Is_True"

private const val codeForCheatActivityForResult = 0

class MainActivity : AppCompatActivity() {

    private lateinit var trueButtonVar: Button
    private lateinit var falseButtonVar: Button
    private lateinit var nextButtonVar: Button
    private lateinit var previewButtonVar: Button
    private lateinit var cheatButtonVar: Button

    private lateinit var textViewQuestionVar: TextView

    private lateinit var quizViewModel: QuizViewModel




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        init()
        createAndBindViewModel()

        val currentIndexFromBundle = savedInstanceState?.getInt(tagForCurrentIndexInBundle) ?: 0
        quizViewModel.currentIndex = currentIndexFromBundle

        quizViewModel.createQuestions()
        fillTextView()

        Log.d(tagFromActivity, "OnCreate")

    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt(tagForCurrentIndexInBundle, quizViewModel.currentIndex)
    }

    override fun onStart() {
        super.onStart()
        Log.d(tagFromActivity, "OnStart")
    }

    override fun onResume() {
        super.onResume()
        Log.d(tagFromActivity, "OnResume")
    }

    override fun onPause() {
        super.onPause()
        Log.d(tagFromActivity, "OnPause")
    }

    override fun onStop() {
        super.onStop()
        Log.d(tagFromActivity, "OnStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(tagFromActivity, "OnDestroy")
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (resultCode != Activity.RESULT_OK){
            return
        }

        if (requestCode == codeForCheatActivityForResult && data != null) {
            quizViewModel.isCheater = data.getBooleanExtra("?????????????????? ???? ??????????????????", false)
        } else {
            return
        }
    }

    fun init(){
        trueButtonVar = findViewById(R.id.id_true_button)
        falseButtonVar = findViewById(R.id.id_false_button)
        nextButtonVar = findViewById(R.id.id_next_button)
        previewButtonVar = findViewById(R.id.id_preview_button)
        textViewQuestionVar = findViewById(R.id.id_textview_question)
        cheatButtonVar = findViewById(R.id.id_cheat_button)

        val instanceTrueListener = trueButtonListener()
        val instanceFalseListener = falseButtonListener()
        val instanceNextListener = nextButtonListener()
        val instancePreviewListener = previewButtonListener()

        trueButtonVar.setOnClickListener(instanceTrueListener)
        falseButtonVar.setOnClickListener(instanceFalseListener)
        nextButtonVar.setOnClickListener(instanceNextListener)
        previewButtonVar.setOnClickListener(instancePreviewListener)


        cheatButtonVar.setOnClickListener(object: View.OnClickListener{
            override fun onClick(viewButton: View?) {
                val intentForCheatActivity = CheatActivity.createIntentForCheatActivity(this@MainActivity,
                        quizViewModel.currentRightAnswer)

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
                    if (viewButton != null) {
                        val optionForAnimation = ActivityOptions.makeClipRevealAnimation(viewButton, 0,0,
                                viewButton.width, viewButton.height)

                        startActivityForResult(intentForCheatActivity, codeForCheatActivityForResult, optionForAnimation.toBundle())

                    } else {
                        startActivityForResult(intentForCheatActivity, codeForCheatActivityForResult)
                    }
                } else {
                    startActivityForResult(intentForCheatActivity, codeForCheatActivityForResult)
                }
            }

        })

    }



    fun fillTextView(){
        val currentQuestionTextId = quizViewModel.currentQuestionTextId
        textViewQuestionVar.setText(currentQuestionTextId)
    }

    fun checkAnswer(userAnswer: Boolean){
        val currentRightAnswer = quizViewModel.currentRightAnswer
        val resultMessageText =
                when {
                    quizViewModel.isCheater == true -> R.string.judgment_toast
                    userAnswer == currentRightAnswer -> R.string.string_correct_answer
                    else -> R.string.string_false_answer
        }

        Toast.makeText(this, resultMessageText, Toast.LENGTH_SHORT).show()
    }

    fun createAndBindViewModel(){
        val vmProvider: ViewModelProvider = ViewModelProvider(this)
        quizViewModel = vmProvider.get(QuizViewModel::class.java)

        Log.d(tagFromActivity, "ViewModel got and bind to Activity. ViewModel - $quizViewModel")
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
            quizViewModel.moveToNextQuestion()
            fillTextView()


        }
    }

    inner class previewButtonListener: View.OnClickListener{
        override fun onClick(v: View?) {
            quizViewModel.moveToPreviewQuestion()
            fillTextView()
        }

    }
}