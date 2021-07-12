package com.example.geoquiz.ViewModel

import androidx.lifecycle.ViewModel

class ViewModel : ViewModel() {

    private var thiscount : Long = 0

    fun getcount() : Long = thiscount

    fun countsave(startcount : Long){ this.thiscount = startcount}

}