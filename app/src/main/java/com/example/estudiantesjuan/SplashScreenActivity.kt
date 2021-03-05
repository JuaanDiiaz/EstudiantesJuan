package com.example.estudiantesjuan

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.example.estudiantesjuan.databinding.ActivityDetailBinding

class SplashScreenActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        supportActionBar?.hide()

        Handler().postDelayed({
            val intent = Intent(this@SplashScreenActivity,FormActivity::class.java)
            startActivity(intent)
            finish()
        },3000L)
    }
}