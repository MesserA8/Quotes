package com.atihon09.quotes

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer

class StartPage : AppCompatActivity() {

    private lateinit var timer: CountDownTimer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start_page)

        timer = object : CountDownTimer(2000, 100) {
            override fun onTick(p0: Long) {

            }

            override fun onFinish() {
                startActivity(Intent(this@StartPage, MainActivity::class.java))
            }
        }.start()
    }
}