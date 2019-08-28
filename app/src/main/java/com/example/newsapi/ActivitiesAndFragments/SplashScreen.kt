package com.example.newsapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.example.newsapi.MainActivity
import com.example.newsapi.R
import kotlinx.android.synthetic.main.activity_main.*

class SplashScreen : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)


        Handler().postDelayed({

            startActivity(Intent(this, MainActivity::class.java))
            finish()
        },2500)
    }
}
