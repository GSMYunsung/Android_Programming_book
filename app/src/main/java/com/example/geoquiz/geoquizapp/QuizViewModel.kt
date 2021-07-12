package com.example.geoquiz.geoquizapp

import androidx.lifecycle.ViewModel
import com.example.geoquiz.R

private const val TAG = "QuizViewModel"

class QuizViewModel : ViewModel() {
    // {} 안의 코드 : 초기화 블록
//    init {
//        Log.d(TAG,"ViewModel instance created")
//    }

    //문제 리스트
     val questionBank = listOf(
        Question(R.string.question_australia,true,userAnswer = false),
        Question(R.string.question_oceans,true,userAnswer = false),
        Question(R.string.question_mideast,false,userAnswer = false),
        Question(R.string.question_africa,false,userAnswer = false),
        Question(R.string.question_americas,true,userAnswer = false),
        Question(R.string.question_asia,true, userAnswer = false)
    )


    //엑티비티 로직 코드를 액티비티와 분리 할 수 있어서 액티비티를 좀더 간단하게 유지가능!
    //액티비티에 추가되는 모든 코드는 뜻하지 않게 액티비티 생명주기를 따를 수 있기때문에 따로 분리해주는것이 좋다!
    var currentIndex = 0

    val currentQuestionAnswer : Boolean
    get() = questionBank[currentIndex].answer

    val currentQuestionText : Int
    get() = questionBank[currentIndex].textResId

    fun moveToNext(){
        currentIndex = (currentIndex + 1) % questionBank.size
    }
}