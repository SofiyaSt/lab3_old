package com.example.lab3

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {
    private val _counter = MutableLiveData<Int>()
    val counter: LiveData<Int> get() = _counter
//нулевое значение счетчика
    init { _counter.value = 0 }
//установка введенного значения
    fun setCounter(value: Int) { _counter.value = value }
//сумма
    fun increaseCounter(amount: Int) {
        val thread = Thread {
            Thread.sleep(3000)
            val updatedValue = (_counter.value ?: 0) + amount
            _counter.postValue(updatedValue)
        }
        thread.start()
    }
//вычитание
    fun decreaseCounter(amount: Int) {
        val thread = Thread {
            Thread.sleep(3000)
            val updatedValue = (_counter.value ?: 0) - amount
            _counter.postValue(updatedValue)
        }
        thread.start()
    }
}