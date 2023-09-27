package com.androidc.incrementdecrementmvvm.data

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.first
import java.text.DecimalFormat

interface CounterDataSource {

    fun getCounterFlow(): Flow<Int>
    fun getPriceFlow(): Flow<String>
    suspend fun increment()
    suspend fun decrement()
    suspend fun totalPrice()
}

class CounterDataSourceImpl : CounterDataSource {

    private val counterFlow = MutableStateFlow(0)
    private val priceFlow = MutableStateFlow("IDR 0")

    override fun getCounterFlow(): Flow<Int> = counterFlow
    override fun getPriceFlow(): Flow<String> = priceFlow

    override suspend fun increment() {
        val currentValue = counterFlow.first()
        val value = currentValue + 1
        counterFlow.emit(value)
    }

    override suspend fun decrement() {
        val currentValue = counterFlow.first()
        if (currentValue <= 0) return
        val value = currentValue - 1
        counterFlow.emit(value)
    }

    override suspend fun totalPrice() {
        val currentValue = counterFlow.first()
        val value = currentValue * 18000
        val formattedTotalPrice = formatPrice(value)
        priceFlow.emit(formattedTotalPrice)
    }

    private fun formatPrice(price: Int): String {
        val formatter = DecimalFormat("#,###")
        return "IDR " + formatter.format(price)
    }
}