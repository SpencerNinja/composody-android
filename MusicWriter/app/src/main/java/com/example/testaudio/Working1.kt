package com.example.testaudio

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.karlotoy.perfectune.instance.PerfectTune
import android.os.CountDownTimer
import android.view.View

class Working1 : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }

    // Instantiate the PerfectTune object
    var perfectTune = PerfectTune()
    var perfectTuneC5 = PerfectTune()
    var perfectTuneD5 = PerfectTune()
    var perfectTuneE5 = PerfectTune()
    var perfectTuneG5 = PerfectTune()

    fun playTune(view: View) {
        if (view.id == R.id.button_sound_play) {
            // Instantiate the PerfectTune object
            perfectTune = PerfectTune()
            // set your desired frequency
            perfectTune.tuneFreq = 440.0
            // set the amplitude (default 10000)
            perfectTune.tuneAmplitude = 10000

            perfectTuneC5.tuneFreq = 523.2511
            perfectTuneD5.tuneFreq = 587.3295
            perfectTuneE5.tuneFreq = 659.2551
            perfectTuneG5.tuneFreq = 783.9909

            // C5
            object : CountDownTimer(500, 1000) {
                override fun onTick(millisUntilFinished: Long) {
                    perfectTuneC5.playTune()
                }
                override fun onFinish() {
                    perfectTuneC5.stopTune()
                    // D5
                    object : CountDownTimer(500, 1000) {
                        override fun onTick(millisUntilFinished: Long) {
                            perfectTuneD5.playTune()
                        }
                        override fun onFinish() {
                            perfectTuneD5.stopTune()
                            // E5
                            object : CountDownTimer(1000, 1000) {
                                override fun onTick(millisUntilFinished: Long) {
                                    perfectTuneE5.playTune()
                                }
                                override fun onFinish() {
                                    perfectTuneE5.stopTune()
                                    // G5
                                    object : CountDownTimer(1000, 1000) {
                                        override fun onTick(millisUntilFinished: Long) {
                                            perfectTuneG5.playTune()
                                        }
                                        override fun onFinish() {
                                            perfectTuneG5.stopTune()
                                            // E5
                                            object : CountDownTimer(1000, 1000) {
                                                override fun onTick(millisUntilFinished: Long) {
                                                    perfectTuneE5.playTune()
                                                }
                                                override fun onFinish() {
                                                    perfectTuneE5.stopTune()
                                                    // D5
                                                    object : CountDownTimer(1000, 1000) {
                                                        override fun onTick(millisUntilFinished: Long) {
                                                            perfectTuneD5.playTune()
                                                        }
                                                        override fun onFinish() {
                                                            perfectTuneD5.stopTune()
                                                            // C5
                                                            object : CountDownTimer(1000, 1000) {
                                                                override fun onTick(millisUntilFinished: Long) {
                                                                    perfectTuneC5.playTune()
                                                                }
                                                                override fun onFinish() {
                                                                    perfectTuneC5.stopTune()
                                                                }
                                                            }.start()
                                                        }
                                                    }.start()
                                                }
                                            }.start()
                                        }
                                    }.start()
                                }
                            }.start()
                        }
                    }.start()
                }
            }.start()

        }
    }

    fun stopTune(view: View) {
        if (view.id == R.id.button_sound_stop) {
            //stops the tune
            perfectTune.stopTune()
        }
    }

}