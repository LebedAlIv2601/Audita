package com.example.audita

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

/**
 * Главная активность приложения
 */
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(R.style.Theme_Audita)
        setContentView(R.layout.activity_main)
    }
}