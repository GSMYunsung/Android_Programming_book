package com.example.geoquiz.LiveData

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

//특정 값을 뷰 모델로 전달해야 하는 경우
class MainActivityViewModelFactory(private val startingTotal: Int) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        //만약 뷰 모델이 LiveDataViewModel 일 경우
        //라이브 데이터 모델에 startingTotal 을 넣어줌
        if(modelClass.isAssignableFrom(LiveDataViewModel::class.java)){
            return LiveDataViewModel(startingTotal) as T
        }
        throw IllegalArgumentException("NO!!")
    }
}