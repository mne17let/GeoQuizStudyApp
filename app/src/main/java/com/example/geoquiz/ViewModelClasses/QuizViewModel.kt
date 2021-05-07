package com.example.geoquiz.ViewModelClasses

import android.util.Log
import androidx.lifecycle.ViewModel

private const val tagFromViewModel = "ViewModel_Vladimir"

class QuizViewModel: ViewModel() {

    init{
        Log.d(tagFromViewModel, "ViewModel created")
    }

    override fun onCleared() {
        super.onCleared()
        Log.d(tagFromViewModel, "ViewModel удалена")
    }
}