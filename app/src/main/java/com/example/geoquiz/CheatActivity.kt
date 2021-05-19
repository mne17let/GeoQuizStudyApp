package com.example.geoquiz

import android.content.Context
import android.content.Intent
import android.opengl.Visibility
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

private const val stringExtraForIntentIfAnswerIsTrue = "Answer_Is_True"

class CheatActivity : AppCompatActivity(){

    private var currentRightAnswerForCheat = false
    private lateinit var textViewWithRightAnswerVar: TextView
    private lateinit var showAnswerButtonVar: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cheat)

        currentRightAnswerForCheat = intent.getBooleanExtra(stringExtraForIntentIfAnswerIsTrue, false)

        init()
    }

    fun init(){
        textViewWithRightAnswerVar = findViewById(R.id.id_textview_answer)
        showAnswerButtonVar = findViewById(R.id.id_show_answer_button)

        textViewWithRightAnswerVar.text = if (currentRightAnswerForCheat == true){
        //currentRightAnswerForCheat.toString()
         "Это правда"
        } else {
          //  currentRightAnswerForCheat.toString()
            "Это неправда"
        }

        setClickListenerOnShowAnswerButton()
    }

    fun setClickListenerOnShowAnswerButton(){

        val listener = object: View.OnClickListener {
            override fun onClick(v: View?) {
                textViewWithRightAnswerVar.visibility = View.VISIBLE
            }
        }

        showAnswerButtonVar.setOnClickListener(listener)
    }


    companion object {

        fun createIntentForCheatActivity(context: Context, trueAnswer: Boolean): Intent{
            val objectIntent = Intent(context, CheatActivity::class.java)
            objectIntent.putExtra(stringExtraForIntentIfAnswerIsTrue, trueAnswer)
            return objectIntent
        }

    }


}