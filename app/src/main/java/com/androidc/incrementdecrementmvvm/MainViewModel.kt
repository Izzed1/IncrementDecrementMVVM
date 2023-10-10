package com.androidc.incrementdecrementmvvm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel: ViewModel() {
    // membuat backing properties
    private val _counter: MutableLiveData<Int> = MutableLiveData<Int>().apply { // ini sifatnya private, isolated
        postValue(0)
    }

    val counter: LiveData<Int> // ini dia yang diexpost ke views
        get() = _counter // meng-overated variable _counter

    fun increment() {
        val currentValue = _counter.value ?: 0
        val value = currentValue + 1
        _counter.postValue(value)
    }

    fun decrement() {
        val currentValue = _counter.value ?: 0
        if (currentValue <= 0) return
        val value = currentValue - 1
        _counter.postValue(value)
    }
}