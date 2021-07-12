package com.example.geoquiz.ViewModel

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.SystemClock
import android.widget.Chronometer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.geoquiz.R

class ViewModelStudy : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_model_study)

        val countViewModel = ViewModelProvider(this)
            .get(com.example.geoquiz.ViewModel.ViewModel::class.java)

        if(countViewModel.getcount() <= 0)
        {
            val startTime = SystemClock.elapsedRealtime()
            countViewModel.countsave(startTime)
            findViewById<Chronometer>(R.id.chronometer).base = startTime
        }
        else
        {
            findViewById<Chronometer>(R.id.chronometer).base = countViewModel.getcount()
        }

        findViewById<Chronometer>(R.id.chronometer).start()
    }
}