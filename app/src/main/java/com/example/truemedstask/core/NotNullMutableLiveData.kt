package com.example.truemedstask.core

import androidx.lifecycle.MutableLiveData

/**
 * @author Rohit
 */
class NotNullMutableLiveData<T : Any>(defaultValue: T) : MutableLiveData<T>() {

    init {
        value = defaultValue
    }

    override fun getValue()  = super.getValue()!!
}