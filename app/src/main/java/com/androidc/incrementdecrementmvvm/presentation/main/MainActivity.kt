package com.androidc.incrementdecrementmvvm.presentation.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.androidc.incrementdecrementmvvm.data.CounterDataSource
import com.androidc.incrementdecrementmvvm.data.CounterDataSourceImpl
import com.androidc.incrementdecrementmvvm.databinding.ActivityMainBinding
import com.androidc.incrementdecrementmvvm.utils.GenericViewModelFactory

class MainActivity : AppCompatActivity() {
    private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    private val viewModel: MainViewModel by viewModels {
        val dataSource: CounterDataSource = CounterDataSourceImpl()
        GenericViewModelFactory.create(MainViewModel(dataSource))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setOnClickListener()
        observeState()
    }

    private fun observeState() {
        viewModel.counter.observe(this) {
            binding.tvCounter.text = it.toString()
        }
        viewModel.Price.observe(this) {
            binding.tvTotalPrice.text = it.toString()
        }
    }

    private fun setOnClickListener() {
        binding.btnDecrement.setOnClickListener {
            decrement()
            totalPrice()
        }
        binding.btnIncrement.setOnClickListener {
            increment()
            totalPrice()
        }
    }

    private fun totalPrice() {
        viewModel.totalPrice()
    }

    private fun decrement() {
        viewModel.decrement()
    }

    private fun increment() {
        viewModel.increment()
    }
}