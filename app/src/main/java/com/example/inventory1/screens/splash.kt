package com.example.inventory1.screens

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.inventory1.MainActivity
import com.example.inventory1.R
import com.example.inventory1.databinding.ActivitySplashBinding

class splash : AppCompatActivity() {
    private lateinit var binding: ActivitySplashBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding =ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.start.setOnClickListener {
            val intent= Intent(this,Home::class.java)
            startActivity(intent)
        }
    }
}