package com.example.geoquiz.LiveData

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.geoquiz.R
import com.example.geoquiz.databinding.ActivityLiveDataBinding

class LiveData : AppCompatActivity() {
    private lateinit var binding: ActivityLiveDataBinding
    private lateinit var viewModelFactory: MainActivityViewModelFactory
    private lateinit var countViewModel : LiveDataViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_live_data)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_live_data)


        viewModelFactory = MainActivityViewModelFactory(0)
        countViewModel = ViewModelProvider(this,viewModelFactory).get(LiveDataViewModel::class.java)

        // ViewModel 로만 설정해주기
//        binding.countText.text = countViewModel.getUpdatedCount().toString()

        // ViewModel + LiveData
        // viewModel 에 일일히 값을 받아오지 않아도 된다!
        countViewModel.countData.observe(this, Observer {
            Log.d("change","값이 변경되었습니다!")
            binding.countText.text = it.toString()
        })

        binding.countBtn.setOnClickListener {
                countViewModel.addCount(Integer.parseInt(binding.countEdit.text.toString()))
                    .toString()
        }
    }
}