package com.example.geoquiz

import androidx.annotation.StringRes

// StringRes 를 쓰는이유!
// 1. 유요한 리소스 ID를 제공하는지 컴파일시점에서 검사해 유효하지않은 변수가 들어오는것을 방지!
//    따라서 앱이 강제중단되는것을 방지할 수 있다.
// 2. 애노테이션을 지정함으로 다른개발자들이 쉽게 코드를 이해할 수 있다.

// Data Class
// 1. equals()
// 2. hashcode()
// 3. toString()

// 문자열의 ID를 가진다!!

// 해당 모델 클래스는 true-false 를 보여주는 위젯에 대해 모른다!
// 즉, 다음 클래스는 범용적으로 사용이 가능하다는 장점이 나타난다.

data class Question(@StringRes val textResId : Int , val answer : Boolean) {

}