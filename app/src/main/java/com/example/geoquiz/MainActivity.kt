package com.example.geoquiz

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.widget.Button
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    var mainActivity = this

    private lateinit var trueButtonVar: Button
    private lateinit var falseButtonVar: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        trueButtonVar = findViewById(R.id.id_true_button)
        falseButtonVar = findViewById(R.id.id_false_button)

        var instanceTrueListener = trueButtonListener()
        var instanceFalseListener = falseButtonListener()

        trueButtonVar.setOnClickListener(instanceTrueListener)
        falseButtonVar.setOnClickListener(instanceFalseListener)
    }

    inner class trueButtonListener: View.OnClickListener{
        override fun onClick(v: View?) {
            val toast = Toast.makeText(mainActivity, "Нажат true", Toast.LENGTH_SHORT)
            toast.show()
        }
    }

    inner class falseButtonListener: View.OnClickListener{
        override fun onClick(v: View?) {
            Toast.makeText(mainActivity, "Нажат false", Toast.LENGTH_SHORT).show()
        }
    }
}