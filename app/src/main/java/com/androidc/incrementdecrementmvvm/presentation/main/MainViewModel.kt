package com.androidc.incrementdecrementmvvm.presentation.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.androidc.incrementdecrementmvvm.data.CounterDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel(
    private val dataSource: CounterDataSource
) : ViewModel() {

    val counter: LiveData<Int>
        get() = dataSource.getCounterFlow().asLiveData(Dispatchers.Main)

    fun increment() {
        viewModelScope.launch {
            dataSource.increment()
        }
    }

    fun decrement() {
        viewModelScope.launch {
            dataSource.decrement()
        }
    }
}