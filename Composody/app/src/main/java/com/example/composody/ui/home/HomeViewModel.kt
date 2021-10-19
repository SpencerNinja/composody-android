package com.example.composody.ui.home

import android.os.CountDownTimer
import android.util.Log
import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.composody.Note
import com.example.composody.R
import com.example.composody.Scale
import com.karlotoy.perfectune.instance.PerfectTune
import kotlin.random.Random

class HomeViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "HOME"
    }
    val text: LiveData<String> = _text

}