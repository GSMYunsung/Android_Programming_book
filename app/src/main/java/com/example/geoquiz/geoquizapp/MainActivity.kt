package com.example.geoquiz.geoquizapp

//위젯 View 객체의 계층구조에 존재함. <- 뷰 계층 구조
//Layout에서 id 속성값에 +가 있는이유 : id값을 생성하기때문에
//안드로이드 애플리케이션은 이벤트 기반이다. 그러한 이벤트에 응답하기위해서 생성하는 객체가 리스너이다.
//리스너는 해당 이벤트의 리스너 인터페이스를 구현함.
//onClick 같이 단일추상클래스를 갖는 자바 인터페이스를 SAM 이라고한다.
//코틀린에서는 자바와의 호환성을위해여 지원하지만 특별한형태로 지원한다.
//람다식 또는 함수리터럴으로 SAM을 작성하면 인터페이스의 구현 겍체로 돌려준다. -> SAM 변환

//문제점 : 화면이 회전할 때 마다 안드로이드 엑티비티를 소멸시키고 새로운 엑티비티를 생성.

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.geoquiz.R

//과거 안드로이드 버전과의 호환성을 지원하기위해! <- AppCompatActivity

    //Bundel 객체에 저장될 데이터 키값
    private const val KEY_INDEX = "index"

class MainActivity : AppCompatActivity() {
    private var UsertrueNum = 0
    private lateinit var trueButton: Button
    private lateinit var falseButton: Button
    private lateinit var nextButton : Button
    private lateinit var questionTextView: TextView

    private val quizViewModel : QuizViewModel by lazy {
        ViewModelProvider(this).get(QuizViewModel::class.java)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //저장한 Index값이 0이 아닐경우 currentIndex에 넣어주고 null이거나 객체가 없는경우 0을 넣어줌.
        val currentIndex = savedInstanceState?.getInt(KEY_INDEX,0) ?: 0
        quizViewModel.currentIndex = currentIndex

        // 현재 액티비티와 연관된 ViewModelProvider 인스턴스가 생성되고 ViewModelProvider 가 ViewModel 인스턴스를 반환
        //    ViewModelProvider(this).get(QuizViewModel::class.java)

        //받아온 View 타입을 Button 타입으로 변환시켜줌
        trueButton = findViewById(R.id.true_button)
        falseButton = findViewById(R.id.false_button)
        nextButton = findViewById(R.id.next_button)
        questionTextView = findViewById(R.id.question_text_view)

        trueButton.setOnClickListener {
            checkAnswer(true)
        }
        falseButton.setOnClickListener {
            checkAnswer(false)
        }

        nextButton.setOnClickListener {
            quizViewModel.moveToNext()
            updateQuestion()
        }

        updateQuestion()

    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        Log.d("ALDJF","onSaveInstanceState")
        //프로세스가 종료될 떄 호출 현재 Index값을 저장함
        outState.putInt(KEY_INDEX,quizViewModel.currentIndex)
    }

    private fun updateQuestion(){
        val questionTextResId = quizViewModel.currentQuestionText
        questionTextView.setText(questionTextResId)
    }

    private fun checkAnswer(userAnswer : Boolean){
        val correctAnswer = quizViewModel.currentQuestionAnswer
        var toastUserAnswer : String = ""
        val messageResId = if (userAnswer == correctAnswer){
            R.string.correct_toast
        }
            else {
            R.string.incorrect_toast
            }

            if( quizViewModel.questionBank[quizViewModel.currentIndex].userAnswer  == false)
            {
                Toast.makeText(this,messageResId,Toast.LENGTH_SHORT).show()
            }


            if (userAnswer == correctAnswer &&  quizViewModel.questionBank[quizViewModel.currentIndex].userAnswer  == false)
            {
                UsertrueNum++
                toastUserAnswer = UsertrueNum.toString().plus('/').plus(quizViewModel.questionBank.size.toString())
                quizViewModel.questionBank[quizViewModel.currentIndex].userAnswer = true
            }
                Toast.makeText(this,toastUserAnswer,Toast.LENGTH_SHORT).show()
        }
    }