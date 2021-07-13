package com.example.geoquiz.LiveData

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class LiveDataViewModel(startingTotal: Int) : ViewModel() {

    //현재 덧셈 결과 값 LiveData 생성 접근 X
    private var count = MutableLiveData<Int>()
    // 다른 클래스가 접근할 수 있는 데이터
    val countData : LiveData<Int>
        get() = count

    init {
        count.value = startingTotal
    }

//    fun getUpdatedCount() : MutableLiveData<Int> {
//        return count
//    }

    // count.value 으로 LiveData 값을 초기화 시킨다.
    fun addCount(plusCount : Int){
        count.value = (count.value)?.plus(plusCount)
    }
}