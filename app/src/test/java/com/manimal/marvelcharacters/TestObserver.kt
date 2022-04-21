package com.manimal.marvelcharacters

import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import kotlinx.coroutines.runBlocking

class TestObserver<T> : Observer<T> {
    val observedValues = mutableListOf<T>()

    override fun onChanged(value: T) {
        observedValues.add(value)
    }
}

fun <T> LiveData<T>.testObserver(block: () -> Unit) = TestObserver<T>()
    .also { observer ->
        try {
            observeForever(observer)
            runBlocking { block() }
        } finally {
            removeObserver(observer)
        }
    }