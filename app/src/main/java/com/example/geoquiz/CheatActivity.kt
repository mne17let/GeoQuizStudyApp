package com.example.geoquiz

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.opengl.Visibility
import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.geoquiz.ViewModelClasses.CheatViewModel

private const val stringExtraForIntentIfAnswerIsTrue = "Answer_Is_True"
private const val stringCodeForBundleForCheckCheatingAfterRotait = "IsCheat"

class CheatActivity : AppCompatActivity(){

    private var currentRightAnswerForCheat = false
    private lateinit var textViewWithRightAnswerVar: TextView
    private lateinit var showAnswerButtonVar: Button
    private var isCheat = false

    private lateinit var cheatViewModel: CheatViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cheat)

        createAndBindViewModel()

        isCheat = savedInstanceState?.getBoolean(stringCodeForBundleForCheckCheatingAfterRotait) ?: false
        sendResultToParentActivity()

        currentRightAnswerForCheat = intent.getBooleanExtra(stringExtraForIntentIfAnswerIsTrue, false)

        init()

        fillData()

    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putBoolean(stringCodeForBundleForCheckCheatingAfterRotait, isCheat)
    }

    fun createAndBindViewModel(){
        val vmProvider = ViewModelProvider(this)
        cheatViewModel = vmProvider.get(CheatViewModel::class.java)
    }

    fun init(){
        textViewWithRightAnswerVar = findViewById(R.id.id_textview_answer)
        showAnswerButtonVar = findViewById(R.id.id_show_answer_button)

        textViewWithRightAnswerVar.text = if (currentRightAnswerForCheat == true){
        //currentRightAnswerForCheat.toString()
         "Это правда" + Build.VERSION.SDK_INT.toString()
        } else {
          //  currentRightAnswerForCheat.toString()
            "Это неправда"
        }

        setClickListenerOnShowAnswerButton()
        
    }

    fun fillData(){

        if (cheatViewModel.isCheatButtonClick == true){
            textViewWithRightAnswerVar.visibility = View.VISIBLE
        }
    }

    fun setClickListenerOnShowAnswerButton(){

        val listener = object: View.OnClickListener {
            override fun onClick(v: View?) {
                textViewWithRightAnswerVar.visibility = View.VISIBLE

                isCheat = true
                cheatViewModel.isCheatButtonClick = true

                sendResultToParentActivity()
            }
        }

        showAnswerButtonVar.setOnClickListener(listener)

    }

    fun sendResultToParentActivity(){
        val intentWithResult = Intent()
        intentWithResult.putExtra("Посмотрел ли подсказку", isCheat)
        setResult(Activity.RESULT_OK, intentWithResult)
    }


    companion object {

        fun createIntentForCheatActivity(context: Context, trueAnswer: Boolean): Intent{
            val objectIntent = Intent(context, CheatActivity::class.java)
            objectIntent.putExtra(stringExtraForIntentIfAnswerIsTrue, trueAnswer)
            return objectIntent
        }

    }


}