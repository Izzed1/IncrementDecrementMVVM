package com.androidc.incrementdecrementmvvm

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.androidc.incrementdecrementmvvm.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private var counter: Int = 0

    private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setOnClickListener()
        setInitState()
    }

    private fun setInitState() {
        binding.tvCounter.text = counter.toString()
    }

    private fun setOnClickListener() {
        binding.btnDecrement.setOnClickListener {
            decrement()
        }
        binding.btnIncrement.setOnClickListener {
            increment()
        }
    }

    private fun decrement() {
        if (counter <= 0) return
        counter -= 1
        binding.tvCounter.text = counter.toString()
    }

    private fun increment() {
        counter += 1
        binding.tvCounter.text = counter.toString()
    }

}