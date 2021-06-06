package com.example.geoquiz

//위젯 View 객체의 계층구조에 존재함. <- 뷰 계층 구조
//Layout에서 id 속성값에 +가 있는이유 : id값을 생성하기때문에
//안드로이드 애플리케이션은 이벤트 기반이다. 그러한 이벤트에 응답하기위해서 생성하는 객체가 리스너이다.
//리스너는 해당 이벤트의 리스너 인터페이스를 구현함.
//onClick 같이 단일추상클래스를 갖는 자바 인터페이스를 SAM 이라고한다.
//코틀린에서는 자바와의 호환성을위해여 지원하지만 특별한형태로 지원한다.
//람다식 또는 함수리터럴으로 SAM을 작성하면 인터페이스의 구현 겍체로 돌려준다. -> SAM 변환

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.widget.Button
import android.widget.TextView
import android.widget.Toast

//과거 안드로이드 버전과의 호환성을 지원하기위해! <- AppCompatActivity
class MainActivity : AppCompatActivity() {

    private lateinit var trueButton: Button
    private lateinit var falseButton: Button
    private lateinit var nextButton : Button
    private lateinit var questionTextView: TextView

    //문제 리스트
    private val questionBank = listOf(
            Question(R.string.question_australia,true),
            Question(R.string.question_oceans,true),
            Question(R.string.question_mideast,true),
            Question(R.string.question_africa,true),
            Question(R.string.question_americas,true),
            Question(R.string.question_asia,true))

    private var currentIndex = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

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
            currentIndex = (currentIndex + 1) % questionBank.size
            updateQuestion()
        }

        updateQuestion()

    }

    private fun updateQuestion(){
        val questionTextResId = questionBank[currentIndex].textResId
        questionTextView.setText(questionTextResId)
    }

    private fun checkAnswer(userAnswer : Boolean){
        val correctAnswer = questionBank[currentIndex].answer

        val messageResId = if (userAnswer == correctAnswer){
            R.string.correct_toast }
            else {
                R.string.incorrect_toast
            }

            Toast.makeText(this,messageResId,Toast.LENGTH_SHORT)
        }
    }